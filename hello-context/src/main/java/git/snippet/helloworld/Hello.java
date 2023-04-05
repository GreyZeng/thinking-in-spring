package git.snippet.helloworld;

public class Hello {
    private String name;

    public String hello() {
        return "hello world by " + name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
