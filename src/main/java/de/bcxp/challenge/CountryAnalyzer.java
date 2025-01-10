package de.bcxp.challenge;

import java.io.IOException;
import java.util.List;

/**
 * This class is responsible for analyzing country data.
 */
public class CountryAnalyzer {

    private final CSVReader csvReader;

    /**
     * Constructs a CountryAnalyzer with the specified CSVReader.
     * @param csvReader The CSVReader to use for reading CSV files.
     */
    public CountryAnalyzer(CSVReader csvReader) {
        this.csvReader = csvReader;
    }

    /**
     * Finds the country with the highest population density.
     * @param filePath The path to the CSV file.
     * @return The name of the country with the highest population density.
     * @throws IOException If an I/O error occurs.
     */
    public String findCountryWithHighestPopulationDensity(String filePath) throws IOException {
        List<String[]> records = csvReader.readCSV(filePath, ";");

        String countryWithHighestDensity = "";
        double highestDensity = 0;

        for (String[] record : records) {
            if (record.length < 5 || record[0].equals("Name")) {
                continue; // Skip header row or invalid rows
            }

            try {
                String country = record[0];
                double population = Double.parseDouble(record[3].replace(".", "").replace(",", "."));
                double area = Double.parseDouble(record[4].replace(".", "").replace(",", "."));

                double density = population / area;

                if (density > highestDensity) {
                    highestDensity = density;
                    countryWithHighestDensity = country;
                }
            } catch (NumberFormatException e) {
                System.err.printf("Invalid number format in record: %s%n", String.join(";", record));
            }
        }

        return countryWithHighestDensity;
    }
}
