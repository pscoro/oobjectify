package com.oobjectify.base;

public class Property {

    private String type = "";
    private String name = "";
    private String initValue = "";
    private String rawText = "";

    public Property(String type, String name, String initValue) {
        this.type = type;
        this.name = name;
        if (initValue.equals("")) initValue = "\"\"";
        this.initValue = initValue;
        this.rawText = "private " + type + " " + name + " = " + initValue + ";";
    }

    public Property(String type, String name, String initValue, boolean isPublic, boolean isStatic, boolean isFinal) {
        this.type = type;
        this.name = name;
        if (initValue.equals("") && type.equals("String")) {
            initValue = "\"\"";
            this.rawText =
                    (isPublic ? "public " : "private ") +
                            (isStatic ? "static " : "") +
                            (isFinal ? "final " : "") +
                            type + " " + name + " = " + initValue + ";";
        } else if (initValue.equals("")) {
            this.rawText =
                    (isPublic ? "public " : "private ") +
                            (isStatic ? "static " : "") +
                            (isFinal ? "final " : "") +
                            type + " " + name + ";";
        }
        this.initValue = initValue;

    }

    public Property(String rawText) {
        this.rawText = rawText;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getInitValue() {
        return initValue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInitValue(String initValue) {
        this.initValue = initValue;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRawText() {
        return rawText;
    }

    public void setRawText(String rawText) {
        this.rawText = rawText;
    }

    @Override
    public String toString() {
        return rawText;
    }
}
