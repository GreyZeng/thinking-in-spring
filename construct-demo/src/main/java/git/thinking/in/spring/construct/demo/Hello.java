package git.thinking.in.spring.construct.demo;

public class Hello {
    private String name;

    public Hello(String name) {
        this.name = name;
    }

    public String hello() {
        return "Hello World!" + name;
    }
}
