package extractors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import prac.shpp.App;
import prac.shpp.dtos.PropertiesDTO;
import prac.shpp.enums.NumberType;
import prac.shpp.extractors.PropertiesExtractor;

import java.io.IOException;
import java.lang.reflect.Field;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class TestPropertiesExtractor {

    App mockApp;

    @BeforeEach
    void setUp() {
        mockApp = Mockito.mock(App.class);
    }

    @Test
    void testNumberTypeStringIsNotSet() {
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
        PropertiesDTO properties = PropertiesExtractor.extractNumberProperties(validFilename);

        Assertions.assertEquals("5", properties.getMinimumNumber());
        Assertions.assertEquals("6", properties.getMaximumNumber());
        Assertions.assertEquals("-1", properties.getStep());
    }
}
