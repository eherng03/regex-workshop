package utils;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class RegexApplier {

    private Map<String, ArrayList<Pattern>> filterMap;

    public RegexApplier(String regexPath) throws IOException {

            filterMap = new HashMap<String, ArrayList<Pattern>>();

            File regexFile = new File(regexPath);
            if(regexFile.isFile()) {

                readFile(regexFile);

            }else{
                File dir = new File(regexPath);
                if(dir.isDirectory()){
                    File[] files = regexFile.listFiles();
                    for (File file : files) {
                        if (file.isDirectory()) {
                            System.out.println("> ERROR: Provide directory with regex files only");
                        } else {
                            readFile(file);
                        }
                    }
                }

            }

    }

    /***
     * Método que extrae todas las regex de un fichero y las añade a un filtro del mapa de filtros
     * @param regexFile
     * @throws IOException
     */
    private void readFile(File regexFile) throws IOException {
        String line = "";
        ArrayList<Pattern> regexList = new ArrayList<Pattern>();
        String filename = regexFile.getName().replace(".txt", "");

        BufferedReader br = new BufferedReader(new FileReader(regexFile));
        while ((line = br.readLine()) != null) {
            try {
                Pattern p = Pattern.compile(line);
                regexList.add(p);

            } catch (PatternSyntaxException ex) {
                System.out.println("#### ERROR ####");
                System.out.println("Regex " + line + "is wrong");
                System.out.println(ex.getMessage());
            }
        }

        filterMap.put(filename, regexList);
    }

    /***
     * Método que aplica los filtros a un texto pasado como argumento
     * @param fileText
     */
    private void applyFilters(String fileText) {
        // Por cada filtro...
        for(String key: filterMap.keySet()){
            // Aplicamos cada regex del filtro...
            for(Pattern pattern: filterMap.get(key)){

                Matcher matcher = pattern.matcher(fileText);
                while (matcher.find()) {
                    System.out.println("Found sensitive text in " + key+ " filter: " + matcher.group());
                    // You can add here which regex hit
                }
            }

        }

    }

    /***
     * Método que lee un fichero y aplica los filtros sobre el texto que contiene
     * @param file
     */
    public void applyFiltersToFile(File file) {
        String line = "";

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));

            String fileText = "";
            while ((line = br.readLine()) != null) {
                fileText += line; // Append each line to the "file" string
            }

            applyFilters(fileText);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
