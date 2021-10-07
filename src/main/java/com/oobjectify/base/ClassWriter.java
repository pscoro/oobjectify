package com.oobjectify.base;
import java.io.*;

public class ClassWriter {

    private String folderPath;

    public ClassWriter(String folderPath) {
        this.folderPath = folderPath;
    }

    public ClassWriter() {
        folderPath = "./";
    }

    public int printRaw(String title, String rawText) {
        return printRaw(title, rawText, true);
    }

    public int printRaw(String title, String rawText, boolean useFolderPath) {
        int returnInt = 0;
        try {
            File myObj = useFolderPath ? new File(folderPath + title) : new File((title));

            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
                returnInt = 0;
            } else {
                System.out.println("File already exists. "  + myObj.getName());
                returnInt = 1;
            }

            FileWriter myWriter = useFolderPath ? new FileWriter(folderPath + title) : new FileWriter((title));
            myWriter.write(rawText);
            myWriter.close();
            System.out.println("Successfully wrote to the file. "  + myObj.getName());
        } catch (IOException e) {
            System.out.println("An IO error occurred.");
            e.printStackTrace();
            returnInt = -1;
        }
        return returnInt;
    }

    public int createClass(ClassForm classForm) {
        if(!classForm.getRawText().equals("")) return printRaw(classForm.getClassName() + ".java", classForm.getRawText());
        return -1;
    }

    public void reloadClasses() {
        try {

            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd \"D:\\dev\\OObjectify\" && gradle run");
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) { break; }
                System.out.println(line);
            }


            System.exit(0);  //change status
        } catch (IOException e) {
            System.out.println("Could not recompile application, terminating. \n\n");
            e.printStackTrace();
            System.exit(0);
        }
    }
}
