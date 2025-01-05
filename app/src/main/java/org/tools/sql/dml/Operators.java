package org.tools.sql.dml;

public enum Operators {
    AND("AND");
    private String text;

    Operators(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
