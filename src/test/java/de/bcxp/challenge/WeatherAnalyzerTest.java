package de.bcxp.challenge;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for WeatherAnalyzer.
 */
public class WeatherAnalyzerTest {

    /**
     * Tests the findDayWithSmallestTempSpread method.
     * @throws IOException If an I/O error occurs.
     */
    @Test
    public void testFindDayWithSmallestTempSpread() throws IOException {
        CSVReader csvReader = new CSVReader();
        WeatherAnalyzer weatherAnalyzer = new WeatherAnalyzer(csvReader);

        List<String[]> testData = List.of(
            new String[]{"Day", "MxT", "MnT"},
            new String[]{"1", "88", "59"},
            new String[]{"2", "79", "63"},
            new String[]{"3", "77", "55"},
            new String[]{"14", "61", "59"}
        );

        // Write test data to a temporary file
        Path tempFile = Files.createTempFile("weather", ".csv");
        Files.write(tempFile, () -> testData.stream()
            .<CharSequence>map(row -> String.join(",", row))
            .iterator());

        // Use the temporary file for testing
        String result = weatherAnalyzer.findDayWithSmallestTempSpread(tempFile.toString(), ",");
        assertEquals("14", result);

        // Clean up the temporary file
        Files.delete(tempFile);
    }
}
