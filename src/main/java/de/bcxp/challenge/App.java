package de.bcxp.challenge;

import java.io.IOException;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 */
public final class App {

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {
        CSVReader csvReader = new CSVReader();
        WeatherAnalyzer weatherAnalyzer = new WeatherAnalyzer(csvReader);
        CountryAnalyzer countryAnalyzer = new CountryAnalyzer(csvReader);

        try {
            String dayWithSmallestTempSpread = weatherAnalyzer.findDayWithSmallestTempSpread("/Users/mertturkekul/programming-challenge/src/main/resources/de/bcxp/challenge/weather.csv", ",");
            System.out.printf("Day with smallest temperature spread: %s%n", dayWithSmallestTempSpread);

            String countryWithHighestPopulationDensity = countryAnalyzer.findCountryWithHighestPopulationDensity("/Users/mertturkekul/programming-challenge/src/main/resources/de/bcxp/challenge/countries.csv");
            System.out.printf("Country with highest population density: %s%n", countryWithHighestPopulationDensity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
