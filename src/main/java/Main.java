import utils.RegexApplier;

import java.io.*;

public class Main {

    public static void main(String args[]){

        //System.out.println("Applying RegexBuilder to file :"+args[0]);
            RegexApplier rs = null;

            String inputPath = "";
            String regexPath = "";
            if(args.length == 2) {
                inputPath = args[0];
                regexPath = args[1];
            }else{
                inputPath = "/resources/inputPath/";
                regexPath = "/resources/regexPath/";
            }
        try {
            rs = new RegexApplier(regexPath);


            File input = new File(inputPath);
            if(input.isDirectory()){
                File[] files = input.listFiles();
                for (File file : files) {
                    if (file.isDirectory()) {
                        System.out.println("> ERROR: Provide directroy with lexicon text files only");
                    } else {
                        rs.applyFiltersToFile(file);
                    }
                }

            }else if(input.isFile()) {
                rs.applyFiltersToFile(input);
            }


            System.out.println("\n");
            System.out.println("Completed!!!");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
