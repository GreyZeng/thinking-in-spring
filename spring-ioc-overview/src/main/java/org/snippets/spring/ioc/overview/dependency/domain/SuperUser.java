package org.snippets.spring.ioc.overview.dependency.domain;

import org.snippets.spring.ioc.overview.dependency.annotation.Super;

@Super
public class SuperUser extends User {
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }

    private String address;
}
