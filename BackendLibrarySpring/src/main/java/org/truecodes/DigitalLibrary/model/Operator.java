package org.truecodes.DigitalLibrary.model;

public enum Operator {
    EQUALS("="),
    LESS_THEN("<"),
    GREATER_THEN(">"),
    LIKE("LIKE"),
    IN("IN");
    private String value;
    Operator(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
