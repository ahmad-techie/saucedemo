package com.saucedemo.util;

import java.io.*;
import java.util.*;

public class CSVReader {

    private final List<List<String>> csvData = new ArrayList<>();

    // Method to read the entire CSV file
    public void readCSV(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // Split the line by commas
                csvData.add(Arrays.asList(values)); // Add as a list to csvData
            }
        }
    }

    // Method to return all rows
    public List<List<String>> getAllRows() {
        return csvData;
    }

    // Method to return a list of prices
    public List<String> getPrices() {
        List<String> prices = new ArrayList<>();
        // Start from row index 1 to skip the header row
        for (int i = 1; i < csvData.size(); i++) {
            prices.add(csvData.get(i).get(1)); // Add the Price column (index 1)
        }
        return prices;
    }

}
