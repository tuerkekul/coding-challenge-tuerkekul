package de.bcxp.challenge;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for CountryAnalyzer.
 */
public class CountryAnalyzerTest {

    /**
     * Tests the findCountryWithHighestPopulationDensity method.
     * @throws IOException If an I/O error occurs.
     */
    @Test
    public void testFindCountryWithHighestPopulationDensity() throws IOException {
        CSVReader csvReader = new CSVReader();
        CountryAnalyzer countryAnalyzer = new CountryAnalyzer(csvReader);

        List<String[]> testData = List.of(
            new String[]{"Name", "Capital", "Accession", "Population", "Area (km²)", "GDP (US$ M)", "HDI", "MEPs"},
            new String[]{"Austria", "Vienna", "1995", "8926000", "83855", "447718", "0.922", "19"},
            new String[]{"Belgium", "Brussels", "Founder", "11566041", "30528", "517609", "0.931", "21"},
            new String[]{"Malta", "Valletta", "2004", "516100", "316", "14859", "0.895", "6"}
        );

        // Write test data to a temporary file
        Path tempFile = Files.createTempFile("countries", ".csv");
        Files.write(tempFile, () -> testData.stream()
            .<CharSequence>map(row -> String.join(";", row))
            .iterator());

        // Use the temporary file for testing
        String result = countryAnalyzer.findCountryWithHighestPopulationDensity(tempFile.toString());
        assertEquals("Malta", result);

        // Clean up the temporary file
        Files.delete(tempFile);
    }
}
