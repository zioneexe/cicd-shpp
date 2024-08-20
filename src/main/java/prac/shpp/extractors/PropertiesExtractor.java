package prac.shpp.extractors;

import prac.shpp.App;
import prac.shpp.dtos.PropertiesDTO;
import prac.shpp.enums.NumberType;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import static prac.shpp.App.LOGGER;

public class PropertiesExtractor {

    private PropertiesExtractor() {
        throw new IllegalStateException("Utility class");
    }

    public static final String NOT_FOUND = "not found";

    public static PropertiesDTO extractNumberProperties(String filename) throws IOException {
        Properties appProperties = new Properties();

        try (InputStream inputStream = App.class.getClassLoader().getResourceAsStream(filename)) {

            if (inputStream == null) {
                LOGGER.error("Could not extract properties file. File not found of is not present in classpath.");

                throw new IllegalStateException("\nCould not extract properties file.\n" +
                                                "File not found of is not present in classpath.");
            }

            try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
                LOGGER.info("App configuration path is: {}", filename);
                appProperties.load(inputStreamReader);
                LOGGER.debug("Properties loaded from path: {}", filename);

                return PropertiesDTO.builder()
                        .minimumNumber(appProperties.getProperty("minimum_number", NOT_FOUND))
                        .maximumNumber(appProperties.getProperty("maximum_number", NOT_FOUND))
                        .step(appProperties.getProperty("step", NOT_FOUND))
                        .build();

            }
        }
    }

    public static NumberType extractNumberType() {
        String numberTypeString = System.getProperty("numberType");

        if (numberTypeString != null) {
            try {
                NumberType numberType = NumberType.valueOf(numberTypeString.toUpperCase());
                LOGGER.info("Type property passed: {}", numberTypeString);
                LOGGER.info("Setting type to {}", numberType);

                return numberType;
            } catch (IllegalArgumentException e) {
                LOGGER.warn("Invalid argument passed: {}. Defaulting to INT.", numberTypeString);
            }
        }

        return NumberType.INT;
    }

}
