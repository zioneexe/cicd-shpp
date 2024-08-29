package validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import prac.shpp.pojo.CalculationProperties;
import prac.shpp.enums.NumberType;
import prac.shpp.validators.PropertiesValidator;

class TestPropertiesValidator {

    @Test
    void testValidateIntegersWithFloatAndDouble() {
        CalculationProperties properties = new CalculationProperties("4", "6", "1");
        NumberType floatType = NumberType.FLOAT;
        NumberType doubleType = NumberType.DOUBLE;

        Assertions.assertTrue(PropertiesValidator.validate(properties, floatType));
        Assertions.assertTrue(PropertiesValidator.validate(properties, doubleType));
    }

    @Test
    void testValidateDecimalWithDecimal() {
        CalculationProperties properties = new CalculationProperties("0.5", "0.6", "0.01");
        NumberType floatType = NumberType.FLOAT;
        NumberType doubleType = NumberType.DOUBLE;

        Assertions.assertTrue(PropertiesValidator.validate(properties, floatType));
        Assertions.assertTrue(PropertiesValidator.validate(properties, doubleType));
    }

    @Test
    void testValidateDecimalWithIntegerTypes() {
        CalculationProperties properties = new CalculationProperties("0.2", "6", "0.1");
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
        CalculationProperties properties = new CalculationProperties("1", "6", "1");
        NumberType numberType = NumberType.INT;

        Assertions.assertTrue(PropertiesValidator.validate(properties, numberType));
    }

    @Test
    void testValidateZeroStep() {
        CalculationProperties properties = new CalculationProperties("4", "6", "0");
        NumberType numberType = NumberType.INT;

        Assertions.assertFalse(PropertiesValidator.validate(properties, numberType));
    }

    @Test
    void testValidateNegativeStep() {
        CalculationProperties properties = new CalculationProperties("4", "6", "-4");
        NumberType numberType = NumberType.INT;

        Assertions.assertTrue(PropertiesValidator.validate(properties, numberType));
    }

    @Test
    void testValidateEqualNumbers() {
        CalculationProperties properties = new CalculationProperties("4", "4", "1");
        NumberType numberType = NumberType.INT;

        Assertions.assertFalse(PropertiesValidator.validate(properties, numberType));
    }

    @Test
    void testValidateInvalidNumbers() {
        CalculationProperties properties = new CalculationProperties("6", "4", "1");
        NumberType numberType = NumberType.INT;

        Assertions.assertFalse(PropertiesValidator.validate(properties, numberType));
    }
}
