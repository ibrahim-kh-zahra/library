package com.prisma.library.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVFileUtils {

    /**
     * Loading data from csv file and return array of all lines
     * @param fileName
     * @return
     */
    public static List<String[]> readDataFromCSV(String fileName) {

        List<String[]> list = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            while (line != null) {
                if (!line.equals("")) {
                    line = preProcessLine(line);
                    String[] attributes = line.split(",");
                    //Replace special character after preprocessing and splitting the line
                    attributes = Arrays.stream(attributes)
                                       .map(item -> item.replace("$$", ",")
                                                        .trim())
                                       .toArray(size -> new String[size]);
                    if (attributes.length > 0) {
                        list.add(attributes);
                    }
                }
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return list;
    }

    /**
     * Some names comes with double quotes and comma between first and last names
     * Fetch these cases replace with special character $$ and then split the line
     * @param line
     * @return
     */
    private static String preProcessLine(String line) {

        Pattern p = Pattern.compile("\"([^\"]*)\"");
        Matcher m = p.matcher(line);
        while (m.find()) {
            String oldString = new String(m.group(1));
            String newString = m.group(1)
                                .replace(",", "$$");

            line = line.replace(oldString, newString);
            line = line.replace("\"", "");
        }
        return line;
    }
}

