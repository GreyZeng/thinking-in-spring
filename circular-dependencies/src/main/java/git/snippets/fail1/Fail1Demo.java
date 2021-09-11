package git.snippets.fail1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class Fail1Demo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类配置为Configuration类
        applicationContext.register(Fail1Demo.class);
        // 是否支持循环依赖
        applicationContext.setAllowCircularReferences(true);
        // 启动
        applicationContext.refresh();
        Student bean = applicationContext.getBean(Student.class);
        System.out.println(bean);
        ClassRoom cl = applicationContext.getBean(ClassRoom.class);
        System.out.println(cl);

        // 关闭上下文
        applicationContext.close();
    }

    @Bean
    public static Student student() {
        Student student = new Student();
        student.setId(1L);
        student.setName("zs");
        return student;
    }

    @Bean
    public static ClassRoom classRoom() {
        ClassRoom classRoom = new ClassRoom();
        classRoom.setName("C111");
        return classRoom;
    }
}
