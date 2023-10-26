package git.thinking.in.spring.inherit.demo;

public class Hello {
    private String name;

    public String hello() {
        return "Hello World!";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
