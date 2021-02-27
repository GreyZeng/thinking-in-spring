package org.snippets.ioc.java.beans;

import java.beans.PropertyEditorSupport;

public class AddressEditor extends PropertyEditorSupport {


    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String[] tokens = text.split("\\|");
        Address address = new Address();
        address.setName(tokens[0]);
        address.setNum(Integer.valueOf(tokens[1]));
        setValue(address);
    }
}
