package prac.shpp.extractors;

import prac.shpp.App;
import prac.shpp.dtos.PropertiesDTO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import static prac.shpp.App.LOGGER;

public class PropertiesExtractor {

    public static final String FILENAME = "app.properties";

    public static final String NOT_FOUND = "not found";

    public static PropertiesDTO extract() throws IOException {
        Properties appProperties = new Properties();

        try (InputStream inputStream = App.class.getClassLoader().getResourceAsStream(FILENAME)) {

            if (inputStream == null) {
                LOGGER.error("Could not extract properties file. File not found of is not present in classpath.");

                throw new IllegalStateException("\nCould not extract properties file.\n" +
                                                "File not found of is not present in classpath.");
            }

            try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
                LOGGER.info("App configuration path is: {}", FILENAME);
                appProperties.load(inputStreamReader);
                LOGGER.debug("Properties loaded from path: {}", FILENAME);

                return PropertiesDTO.builder()
                        .minimumNumber(appProperties.getProperty("minimum_number", NOT_FOUND))
                        .maximumNumber(appProperties.getProperty("maximum_number", NOT_FOUND))
                        .step(appProperties.getProperty("step", NOT_FOUND))
                        .build();

            } catch (FileNotFoundException e) {
                LOGGER.error("File to extract properties not found. Exception", e);

                throw new FileNotFoundException("File to extract properties not found.");
            }
        }
    }

}
