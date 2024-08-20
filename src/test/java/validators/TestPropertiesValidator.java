package validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import prac.shpp.dtos.PropertiesDTO;
import prac.shpp.enums.NumberType;
import prac.shpp.validators.PropertiesValidator;

class TestPropertiesValidator {

    @Test
    void testValidateIntegersWithFloatAndDouble() {
        PropertiesDTO properties = new PropertiesDTO("4", "6", "1");
        NumberType floatType = NumberType.FLOAT;
        NumberType doubleType = NumberType.DOUBLE;

        Assertions.assertTrue(PropertiesValidator.validate(properties, floatType));
        Assertions.assertTrue(PropertiesValidator.validate(properties, doubleType));
    }

    @Test
    void testValidateDecimalWithDecimal() {
        PropertiesDTO properties = new PropertiesDTO("0.5", "0.6", "0.01");
        NumberType floatType = NumberType.FLOAT;
        NumberType doubleType = NumberType.DOUBLE;

        Assertions.assertTrue(PropertiesValidator.validate(properties, floatType));
        Assertions.assertTrue(PropertiesValidator.validate(properties, doubleType));
    }

    @Test
    void testValidateDecimalWithIntegerTypes() {
        PropertiesDTO properties = new PropertiesDTO("0.2", "6", "0.1");
        NumberType byteType = NumberType.BYTE;
        NumberType shortType = NumberType.SHORT;
        NumberType intType = NumberType.INT;
        NumberType longType = NumberType.LONG;

        Assertions.assertFalse(PropertiesValidator.validate(properties, byteType));
        Assertions.assertFalse(PropertiesValidator.validate(properties, shortType));
        Assertions.assertFalse(PropertiesValidator.validate(properties, intType));
        Assertions.assertFalse(PropertiesValidator.validate(properties, longType));
    }

    @Test
    void testValidateIntegersWithInt() {
        PropertiesDTO properties = new PropertiesDTO("1", "6", "1");
        NumberType numberType = NumberType.INT;

        Assertions.assertTrue(PropertiesValidator.validate(properties, numberType));
    }

    @Test
    void testValidateZeroStep() {
        PropertiesDTO properties = new PropertiesDTO("4", "6", "0");
        NumberType numberType = NumberType.INT;

        Assertions.assertFalse(PropertiesValidator.validate(properties, numberType));
    }

    @Test
    void testValidateNegativeStep() {
        PropertiesDTO properties = new PropertiesDTO("4", "6", "-4");
        NumberType numberType = NumberType.INT;

        Assertions.assertFalse(PropertiesValidator.validate(properties, numberType));
    }

    @Test
    void testValidateEqualNumbers() {
        PropertiesDTO properties = new PropertiesDTO("4", "4", "1");
        NumberType numberType = NumberType.INT;

        Assertions.assertFalse(PropertiesValidator.validate(properties, numberType));
    }

    @Test
    void testValidateInvalidNumbers() {
        PropertiesDTO properties = new PropertiesDTO("6", "4", "1");
        NumberType numberType = NumberType.INT;

        Assertions.assertFalse(PropertiesValidator.validate(properties, numberType));
    }
}
