package com.oobjectify.base;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class Method {

    private String name = "";
    private String returnType = "";
    private Hashtable<String, String> parameters;
    private ArrayList<String> bodyText;
    private boolean isPublic, isStatic;

    public Method(String name, String returnType, ArrayList<String> bodyText, boolean isPublic, boolean isStatic) {
        this.isPublic = isPublic;
        this.isStatic = isStatic;
        this.parameters = new Hashtable<String, String>();  // NAME, TYPE    // TYPE IS SECOND
        this.name = name;
        this.returnType = returnType;
        this.bodyText = bodyText;
    }

    public Method(String name, String returnType, Hashtable<String, String> parameters, ArrayList<String> bodyText, boolean isPublic, boolean isStatic) {
        this.isPublic = isPublic;
        this.isStatic = isStatic;
        this.parameters = parameters;
        this.name = name;
        this.returnType = returnType;
        this.bodyText = bodyText;
    }

    public Method(String name, String returnType, ArrayList<String> bodyText) {
        this.isPublic = false;
        this.isStatic = false;
        this.parameters = new Hashtable<String, String>();
        this.name = name;
        this.returnType = returnType;
        this.bodyText = bodyText;
    }

    public Method(String name, String returnType, Hashtable<String, String> parameters, ArrayList<String> bodyText) {
        this.isPublic = false;
        this.isStatic = false;
        this.parameters = parameters;
        this.name = name;
        this.returnType = returnType;
        this.bodyText = bodyText;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public void setParameters(Hashtable<String, String> parameters) {
        this.parameters = parameters;
    }

    public void setBodyText(ArrayList<String> bodyText) {
        this.bodyText = bodyText;
    }

    public String getName() {
        return name;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    @Override
    public String toString() {
        String returnString = ((isPublic) ? "public" : "private") + ((isStatic) ? " static" : "")  + (returnType.equals("") ? "" : " " ) + returnType + " " + name + "(";
        Enumeration enu = parameters.keys();
        while (enu.hasMoreElements()) {
            String cur = enu.nextElement().toString();
            returnString += parameters.get(cur) + " " + cur + ", ";
        }
        if(returnString.lastIndexOf(",") == returnString.length()-2)
            returnString = returnString.substring(0, returnString.length()-2);
        returnString += ") {\n";
        for(String line : bodyText) {
            returnString += line + "\n";
        }
        returnString += "}";
        return returnString;
    }

    public void setStatic(boolean aStatic) {
        isStatic = aStatic;
    }

    public String getReturnType() {
        return returnType;
    }

    public ArrayList<String> getBodyText() {
        return bodyText;
    }


    public Hashtable<String, String> getParameters() {
        return parameters;
    }
}
