package com.saucedemo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

public class CSVReader {

    private static final Logger logger = LoggerFactory.getLogger("CSV Reader");

    public static List<String> readCSV(String filePath){
        List<List<String>> csvData = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                csvData.add(Arrays.asList(values));
            }
        }catch (IOException e){

        }

        List<String> prices = new ArrayList<>();
        // Start from row index 1 to skip the header row
        for (int i = 1; i < csvData.size(); i++) {
            prices.add(csvData.get(i).get(1));
        }
        return prices;
    }

}
