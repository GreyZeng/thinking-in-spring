package org.snippets.ioc.java.beans;

import java.beans.PropertyEditorSupport;

public class StringToIntegerPropertyEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(Integer.valueOf(text));
    }
}
