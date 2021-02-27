package org.snippets.ioc.java.beans;

public class Address {
    private String name;
    private Integer num;

    @Override
    public String toString() {
        return "Address{" +
                "name='" + name + ", " + num + " 号" +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
