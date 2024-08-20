package validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import prac.shpp.enums.NumberType;
import prac.shpp.validators.OverflowValidator;

import java.math.BigDecimal;

import static prac.shpp.validators.OverflowValidator.OVERFLOW;

class TestOverflowValidator {

    @Test
    void testMarkIfOverflowed() {
        BigDecimal overflow = BigDecimal.valueOf(987878787);
        BigDecimal notOverflow = BigDecimal.valueOf(5);

        NumberType byteType = NumberType.BYTE;

        Assertions.assertEquals(OVERFLOW, OverflowValidator.markIfOverflowed(overflow, byteType));
        Assertions.assertEquals(notOverflow, OverflowValidator.markIfOverflowed(notOverflow, byteType));
    }

    @Test
    void testCheckIfOverflowed() {
        BigDecimal overflow = OVERFLOW;
        BigDecimal notOverflow = BigDecimal.valueOf(5);

        Assertions.assertFalse(OverflowValidator.checkIfOverflowed(notOverflow));
        Assertions.assertTrue(OverflowValidator.checkIfOverflowed(overflow));
    }

    @Test
    void testValidate() {
        Assertions.assertTrue(OverflowValidator.validate(BigDecimal.ONE, NumberType.INT));
        Assertions.assertFalse(OverflowValidator.validate(new BigDecimal("947499899897497497497"), NumberType.LONG));
    }
}
