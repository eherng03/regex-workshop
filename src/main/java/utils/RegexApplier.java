package utils;


import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class RegexApplier {

    private ArrayList<Pattern> regexList;

    public RegexApplier(String regexPath) throws IOException {

            regexList = new ArrayList<Pattern>();

            File regexFile = new File(regexPath);
            if(regexFile.isFile()) {
                String line = "";

                BufferedReader br = new BufferedReader(new FileReader(regexFile));
                while ((line = br.readLine()) != null) {
                    try {
                        Pattern p = Pattern.compile(line);
                        this.regexList.add(p);

                    } catch (PatternSyntaxException ex) {
                        System.out.println("#### ERROR ####");
                        System.out.println("Regex " + line + "is wrong");
                        System.out.println(ex.getMessage());
                    }
                }
            }

    }

    private void applyFilters(String fileText, String filename) {
        for(Pattern patt: regexList){
            Matcher matcher = patt.matcher(fileText);
            while (matcher.find()) {
                System.out.println("Found sensitive text in " + filename+ ": " + matcher.group());
                // You can add here which regex hit
            }
        }

    }

    public void applyFiltersToFile(File file) {
        String line = "";

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));

            String fileText = "";
            while ((line = br.readLine()) != null) {
                fileText += line; // Append each line to the "file" string
            }

            applyFilters(fileText, file.getName());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
