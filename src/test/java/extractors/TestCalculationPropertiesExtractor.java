package extractors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import prac.shpp.pojo.CalculationProperties;
import prac.shpp.enums.NumberType;
import prac.shpp.extractors.PropertiesExtractor;

import java.io.IOException;

class TestCalculationPropertiesExtractor {

    @Test
    void testNumberTypeStringIsNotSet() {
        System.clearProperty("numberType");

        NumberType numberType = PropertiesExtractor.extractNumberType();

        Assertions.assertEquals(NumberType.INT, numberType);
    }

    @Test
    void testNumberTypeStringIsNotValid() {
        System.setProperty("numberType", "nonsense");

        NumberType numberType = PropertiesExtractor.extractNumberType();

        Assertions.assertEquals(NumberType.INT, numberType);
    }

    @Test
    void testNumberTypeStringIsValid() {
        System.setProperty("numberType", "shOrt");

        NumberType numberType = PropertiesExtractor.extractNumberType();

        Assertions.assertEquals(NumberType.SHORT, numberType);
    }

    @Test
    void testExtractNumberPropertiesFileNotFound() {
        IllegalStateException exception = Assertions.assertThrows(
                IllegalStateException.class,
                () -> PropertiesExtractor.extractNumberProperties("not_found")
        );

        Assertions.assertTrue(exception.getMessage().contains("Could not extract properties file"));
    }

    @Test
    void testExtractNumberPropertiesValidFile() throws IOException {
        String validFilename = "test.properties";
        CalculationProperties properties = PropertiesExtractor.extractNumberProperties(validFilename);

        Assertions.assertEquals("5", properties.getMinimumNumber());
        Assertions.assertEquals("6", properties.getMaximumNumber());
        Assertions.assertEquals("-1", properties.getStep());
    }
}
