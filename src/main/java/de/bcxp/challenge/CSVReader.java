package de.bcxp.challenge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for reading CSV files.
 */
public class CSVReader {

    /**
     * Reads a CSV file and returns the data as a list of string arrays.
     * @param filePath The path to the CSV file.
     * @param delimiter The delimiter used in the CSV file.
     * @return A list of string arrays containing the data from the CSV file.
     * @throws IOException If an I/O error occurs.
     */
    public List<String[]> readCSV(String filePath, String delimiter) throws IOException {
        List<String[]> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(delimiter);
                records.add(values);
            }
        }
        return records;
    }
}


