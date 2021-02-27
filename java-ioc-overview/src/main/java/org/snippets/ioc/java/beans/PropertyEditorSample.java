package org.snippets.ioc.java.beans;

import java.beans.BeanInfo;
import java.beans.PropertyEditor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;
import java.util.stream.Stream;

import static java.beans.Introspector.getBeanInfo;
import static java.beans.PropertyEditorManager.findEditor;
import static java.beans.PropertyEditorManager.registerEditor;

public class PropertyEditorSample {
    public static User convert(Map<String, String> parameters) throws Exception {
        //注册bean的编辑器,放到一个WeakHashMap中
        registerEditor(Address.class, AddressEditor.class);
        registerEditor(Date.class, DateEditor.class);
        User user = new User();
        // 传入Object.class 防止识别出Class.class为propertyType
        BeanInfo bi = getBeanInfo(User.class, Object.class);
        //获取所有的属性
        Stream.of(bi.getPropertyDescriptors()).forEach(pd -> {
            Class<?> propertyType = pd.getPropertyType();
            Method writeMethod = pd.getWriteMethod();
            /*if (propertyType == Class.class) {
                //ignore
            } else*/
            if (propertyType == String.class) {
                try {
                    writeMethod.invoke(user, parameters.get(pd.getName()));
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            } else {
                //根据类型查找bean的编辑器,获取到了对应类型的Editor的实例
                PropertyEditor editor = findEditor(propertyType);
                if (editor != null) {
                    editor.setAsText(parameters.get(pd.getName()));
                    try {
                        writeMethod.invoke(user, editor.getValue());
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("no editor for:" + pd.getName());
                }
            }
        });

        return user;
    }

}
