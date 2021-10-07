package com.oobjectify.oobjects;

import java.util.Random;

public class OObject {

    public enum Extendable {
        OOBJECT(0), OTANGIBLE(1);

        private int numVal;

        Extendable(int numVal) {
            this.numVal = numVal;
        }

        public int getNumVal() {
            return numVal;
        }
    }

    private final int O_ID;
    private String name = "";

    public OObject() {
        Random random = new Random();
        O_ID = random.nextInt();
    }

    public OObject(String name) {
        Random random = new Random();
        O_ID = random.nextInt();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getO_ID() {
        return O_ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "oobjects.OObject #" + O_ID + ": " + name;
    }
}
