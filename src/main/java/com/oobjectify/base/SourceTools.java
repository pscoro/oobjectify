package com.oobjectify.base;

public class SourceTools {

    private String rawText = "";
    private final boolean readOnly;

    public SourceTools(String rawText, boolean readOnly) {
        this.readOnly = readOnly;
        this.rawText = rawText;
    }

    public SourceTools(String rawText) {
        this.readOnly = false;
        this.rawText = rawText;
    }

    public String getRawText() {
        return rawText;
    }

    public void setRawText(String rawText) {
        this.rawText = rawText;
    }

    public void replaceLine(int lineNumber, String newText) {
        for(int i = 0; i < countLines(rawText); i++) {

        }
    }

    public static int countLines(String str){
        String[] lines = str.split("\r\n|\r|\n");
        return  lines.length;
    }

    public void replaceFromStart(int lineNumber, String newText) {

    }

    public int searchForLine(String searchQuery) {
        int i = rawText.indexOf(searchQuery);
        return countLines(rawText.substring(0, i));
    }

//    public int[] searchForLines(String searchQuery) {
//
//    }

    public void insertLine(int lineNumber, String lineText) {

    }

    public void insertLines(int startingLineNumber, String[] lineText) {

    }

}
