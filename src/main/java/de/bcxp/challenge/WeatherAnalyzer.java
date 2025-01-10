package de.bcxp.challenge;

import java.io.IOException;
import java.util.List;

/**
 * This class is responsible for analyzing weather data.
 */
public class WeatherAnalyzer {

    private final CSVReader csvReader;

    /**
     * Constructs a WeatherAnalyzer with the specified CSVReader.
     * @param csvReader The CSVReader to use for reading CSV files.
     */
    public WeatherAnalyzer(CSVReader csvReader) {
        this.csvReader = csvReader;
    }

    /**
     * Finds the day with the smallest temperature spread.
     * @param filePath The path to the CSV file.
     * @param delimiter The delimiter used in the CSV file.
     * @return The day with the smallest temperature spread.
     * @throws IOException If an I/O error occurs.
     */
    public String findDayWithSmallestTempSpread(String filePath, String delimiter) throws IOException {
        List<String[]> records = csvReader.readCSV(filePath, delimiter);

        String dayWithSmallestSpread = "";
        double smallestSpread = Double.MAX_VALUE;

        for (String[] record : records) {
            if (record.length < 3 || record[0].equals("Day")) {
                continue; // Skip header row or invalid rows
            }

            try {
                String day = record[0];
                double maxTemp = Double.parseDouble(record[1]);
                double minTemp = Double.parseDouble(record[2]);

                double spread = maxTemp - minTemp;

                if (spread < smallestSpread) {
                    smallestSpread = spread;
                    dayWithSmallestSpread = day;
                }
            } catch (NumberFormatException e) {
                System.err.printf("Invalid number format in record: %s%n", String.join(",", record));
            }
        }

        return dayWithSmallestSpread;
    }
}
