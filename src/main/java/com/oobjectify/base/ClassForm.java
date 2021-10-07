package com.oobjectify.base;

import com.oobjectify.oobjects.OObject;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class ClassForm {

    private String className = "";
    private String extendsFrom = "";
    private String implementsFrom = "";
    private String rawText = "";
    private Hashtable<String, Property> propertiesTable;
    private Hashtable<String, Method> methodTable;
    private ArrayList<String> imports;

    public ClassForm() {
        propertiesTable = new Hashtable<String, Property>();
        methodTable = new Hashtable<String, Method>();
    }

    public void regenRawText() {
        SourceTools sourceTools = new SourceTools(rawText);

        String suffix = "";
        if(!extendsFrom.equals("")) suffix += "extends " + extendsFrom;
        if(!implementsFrom.equals("")) suffix += "implements " + implementsFrom;

        String newRawText = "package com.oobjectify.oobjects;\n\n";

        if (imports != null && imports.size() != 0) {
            for (String imp : imports) {
                newRawText += imp + "\n";
            }
        }

        newRawText+="\npublic class " + className + " " + suffix + " {\n\n";

        Enumeration enu = propertiesTable.keys();

        while(enu.hasMoreElements()) {
            newRawText += propertiesTable.get(enu.nextElement()).toString() + "\n";
        }

        newRawText+="\n";

        Enumeration enu2 = methodTable.keys();

        while(enu2.hasMoreElements()) {
            newRawText += methodTable.get(enu2.nextElement()).toString() + "\n\n";
        }

        newRawText+="}";

        rawText = newRawText;

        rawText = formatText(rawText);
    }

    public String formatText(String text) {

        int currentDepth = 0;
        int indent = 0;
        int indentNext = 0;
        int prev = -1;

        String newText = "";

        for(int i = 0; i<SourceTools.countLines(text); i++) {
            String cur = "";

            if(text.indexOf("\n", prev+1) == -1) cur = text.substring(prev+1);
            else cur = text.substring(prev+1, text.indexOf("\n", prev+1)+1);

            if (cur.contains("{")) indent++;
            if (cur.contains("}")) indent--;



            for (int j = 0; j < indentNext; j++) {
                cur = "\t" + cur;
                currentDepth++;
            }

            if (indent > 0) indentNext++;
            if (indent < 0) indentNext--;

            for (int j = 0; j > indent; j--) {
                if(!cur.substring(1).equals(""))
                    cur = cur.substring(1);
                currentDepth--;
            }


            indent = 0;
            prev = text.indexOf("\n", prev+1);

            newText += cur;
        }
        return newText;
    }

    public void addProperty(Property property, boolean hasGetter, boolean hasSetter, boolean hasZeroValueCheck) {
        propertiesTable.put(property.getName(), property);
        if (hasGetter) {
            ArrayList<String> body = new ArrayList<>();
            body.add("return " + property.getName() + ";");
            addMethod(new Method("get" + Character.toUpperCase(property.getName().charAt(0)) + property.getName().substring(1),
                    property.getType(), body));
        }
        if (hasSetter) {
            ArrayList<String> body = new ArrayList<>();
            body.add("this." + property.getName() + " = " + property.getName() + ";");
            Hashtable<String, String> paramsTable = new Hashtable<String, String>();
            paramsTable.put(property.getName(), property.getType());
            addMethod(new Method("set" + Character.toUpperCase(property.getName().charAt(0)) + property.getName().substring(1),
                    "void", paramsTable, body));
        }
    }

    public void addConstructor(Hashtable<String, String> propParams,
                               Hashtable<String, String> otherParams,
                               Hashtable<String, String> overwritePropAssign,
                               ArrayList<String> otherLines) { // object vars get initialized to prop params
        otherParams.putAll(propParams);
        ArrayList<String> bodyText = new ArrayList<>();
        Enumeration enu = propParams.keys();
        while(enu.hasMoreElements()) {
            String cur = enu.nextElement().toString();
            if (overwritePropAssign.get(cur) != null && !overwritePropAssign.get(cur).equals(""))
                bodyText.add("this." + cur + " = " + overwritePropAssign.get(cur) + ";\n");
            else bodyText.add("this." + cur + " = " + cur + ";\n");
        }
        for(String line : otherLines) bodyText.add(line + "\n");
        addMethod(new Method(className, "", otherParams, bodyText, true, false));
    }

    public void removeProperty(String name) {
        propertiesTable.remove(name);
    }

    public void addMethod(Method method) {
        methodTable.put(method.getName(), method);
    }

    public void removeMethod(String name) {
        methodTable.remove(name);
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setExtendsFrom(String extendsFrom) {
        this.extendsFrom = extendsFrom;
    }

    public void setExtendsFrom(OObject.Extendable extendsFrom) {
        switch (extendsFrom) {
            case OOBJECT:
                this.extendsFrom = "OObject";
                break;
            case OTANGIBLE:
                this.extendsFrom = "OTangible";
                break;

                // ADD HERE

            default:

        }
    }

    public void setImplementsFrom(String implementsFrom) {
        this.implementsFrom = implementsFrom;
    }

    public void setRawText(String rawText) {
        this.rawText = rawText;
    }

    public void setImports(ArrayList<String> imports) {
        this.imports = imports;
    }

    @Override
    public String toString() {
        return rawText;
    }

    public ArrayList<String> getImports() {
        return imports;
    }

    public Hashtable<String, Method> getMethodTable() {
        return methodTable;
    }

    public Hashtable<String, Property> getPropertiesTable() {
        return propertiesTable;
    }

    public void setMethodTable(Hashtable<String, Method> methodTable) {
        this.methodTable = methodTable;
    }

    public void setPropertiesTable(Hashtable<String, Property> propertiesTable) {
        this.propertiesTable = propertiesTable;
    }

    public String getClassName() {
        return className;
    }

    public String getExtendsFrom() {
        return extendsFrom;
    }

    public String getImplementsFrom() {
        return implementsFrom;
    }

    public String getRawText() {
        return rawText;
    }

    public boolean isExtending() {
        return !extendsFrom.equals("");
    }

    public boolean isImplementing() {
        return !implementsFrom.equals("");
    }
}
