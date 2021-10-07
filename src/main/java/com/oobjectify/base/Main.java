package com.oobjectify.base;


import com.oobjectify.oobjects.OObject;

import java.util.ArrayList;
import java.util.Hashtable;

public class Main {

    public static void main(String[] args) {
        ClassWriter classWriter = new ClassWriter("./src/main/java/com/oobjectify/oobjects/");

        ClassForm oreForm = new ClassForm();
        oreForm.setClassName("Ore");

        oreForm.setExtendsFrom(OObject.Extendable.OOBJECT);
        oreForm.addProperty(new Property("String", "type", "", false, false, false), true, true, false);
        oreForm.addProperty(new Property("double", "purity", "", false, false, false), true, true, false);

        Hashtable<String, String> propParams = new Hashtable<String, String>();
        Hashtable<String, String> otherParams = new Hashtable<String, String>();
        Hashtable<String, String> overwritePropAssign = new Hashtable<String, String>();
        ArrayList<String> otherLines = new ArrayList<>();

        propParams.put("type", "String");
        propParams.put("purity", "int");
        otherLines.add("System.out.println(\"constructor test\");");
        otherLines.add("setName(\"ore_\" + type);");

        overwritePropAssign.put("purity", "purity/100.0");
        oreForm.addConstructor(propParams, otherParams, overwritePropAssign, otherLines);

        ArrayList<String> imports = new ArrayList<>();
        imports.add("import com.oobjectify.oobjects.OObject;");
        oreForm.setImports(imports);

        oreForm.regenRawText();

        int exists = classWriter.createClass(oreForm);
        if(exists == 0) classWriter.reloadClasses();

//#001        Ore ironOre = new Ore("iron", 9);
//#002        System.out.println(ironOre.toString());


    }
}
