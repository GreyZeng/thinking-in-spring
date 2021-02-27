package org.snippets.ioc.java.beans;

import java.util.HashMap;
import java.util.Map;

/**
 * JavaBeans作为IoC容器
 */
public class JavaBeansDemo {
    public static void main(String[] args) throws Exception {
        Map<String, String> parameters = new HashMap<String, String>() {
            {
                //这里的key要和Node里面的属性名一致
                put("name", "福尔摩斯");
                put("address", "贝克街|221");
                put("birthday", "1854-01-06");
            }
        };
        User convert = PropertyEditorSample.convert(parameters);
        System.out.println(convert);

    }
}
