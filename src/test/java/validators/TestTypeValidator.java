package validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import prac.shpp.validators.TypeValidator;

import java.math.BigDecimal;

class TestTypeValidator {

    @Test
    void testIsByte() {
        Assertions.assertTrue(TypeValidator.isByte(new BigDecimal("0")));
        Assertions.assertTrue(TypeValidator.isByte(new BigDecimal("0.00")));
        Assertions.assertFalse(TypeValidator.isByte(new BigDecimal("0.05")));
        Assertions.assertTrue(TypeValidator.isByte(new BigDecimal("-0")));
        Assertions.assertTrue(TypeValidator.isByte(new BigDecimal(Byte.MIN_VALUE)));
        Assertions.assertFalse(TypeValidator.isByte(new BigDecimal(Byte.MIN_VALUE - 1)));
        Assertions.assertTrue(TypeValidator.isByte(new BigDecimal(Byte.MAX_VALUE)));
        Assertions.assertFalse(TypeValidator.isByte(new BigDecimal(Byte.MAX_VALUE + 1)));
        Assertions.assertFalse(TypeValidator.isByte(new BigDecimal("5000")));
        Assertions.assertFalse(TypeValidator.isByte(new BigDecimal("-400")));
    }

    @Test
    void testIsShort() {
        Assertions.assertTrue(TypeValidator.isShort(new BigDecimal("0")));
        Assertions.assertTrue(TypeValidator.isShort(new BigDecimal("0.00")));
        Assertions.assertFalse(TypeValidator.isShort(new BigDecimal("0.05")));
        Assertions.assertTrue(TypeValidator.isShort(new BigDecimal("-0")));
        Assertions.assertTrue(TypeValidator.isShort(new BigDecimal(Short.MIN_VALUE)));
        Assertions.assertFalse(TypeValidator.isShort(new BigDecimal(Short.MIN_VALUE - 1)));
        Assertions.assertTrue(TypeValidator.isShort(new BigDecimal(Short.MAX_VALUE)));
        Assertions.assertFalse(TypeValidator.isShort(new BigDecimal(Short.MAX_VALUE + 1)));
        Assertions.assertFalse(TypeValidator.isShort(new BigDecimal("500000000")));
        Assertions.assertFalse(TypeValidator.isShort(new BigDecimal("-40000")));
    }

    @Test
    void testIsInt() {
        Assertions.assertTrue(TypeValidator.isInt(new BigDecimal("0")));
        Assertions.assertTrue(TypeValidator.isInt(new BigDecimal("0.00")));
        Assertions.assertFalse(TypeValidator.isInt(new BigDecimal("0.05")));
        Assertions.assertTrue(TypeValidator.isInt(new BigDecimal("-0")));
        Assertions.assertTrue(TypeValidator.isInt(new BigDecimal(Integer.MIN_VALUE)));
        Assertions.assertFalse(TypeValidator.isInt(new BigDecimal("-2147483649")));
        Assertions.assertTrue(TypeValidator.isInt(new BigDecimal(Integer.MAX_VALUE)));
        Assertions.assertFalse(TypeValidator.isInt(new BigDecimal("2147483648")));
        Assertions.assertFalse(TypeValidator.isInt(new BigDecimal("-8787888888888")));
        Assertions.assertFalse(TypeValidator.isInt(new BigDecimal("8787288878888")));
    }

    @Test
    void testIsLong() {
        Assertions.assertTrue(TypeValidator.isLong(new BigDecimal("0")));
        Assertions.assertTrue(TypeValidator.isLong(new BigDecimal("0.00")));
        Assertions.assertFalse(TypeValidator.isLong(new BigDecimal("0.05")));
        Assertions.assertTrue(TypeValidator.isLong(new BigDecimal("-0")));
        Assertions.assertTrue(TypeValidator.isLong(new BigDecimal(Long.MIN_VALUE)));
        Assertions.assertFalse(TypeValidator.isLong(new BigDecimal("-9223372036854775809")));
        Assertions.assertTrue(TypeValidator.isLong(new BigDecimal(Long.MAX_VALUE)));
        Assertions.assertFalse(TypeValidator.isLong(new BigDecimal("9223372036854775808")));
        Assertions.assertTrue(TypeValidator.isLong(new BigDecimal("-8787888888888")));
        Assertions.assertFalse(TypeValidator.isLong(new BigDecimal("878728887888800000000000000000000")));
    }

    @Test
    void testIsFloat() {
        Assertions.assertTrue(TypeValidator.isFloat(new BigDecimal("0")));
        Assertions.assertTrue(TypeValidator.isFloat(new BigDecimal("0.00")));
        Assertions.assertTrue(TypeValidator.isFloat(new BigDecimal("0.05")));
        Assertions.assertTrue(TypeValidator.isFloat(new BigDecimal("5.05")));
        Assertions.assertTrue(TypeValidator.isFloat(new BigDecimal("4.0000")));
        Assertions.assertTrue(TypeValidator.isFloat(new BigDecimal("-0")));
        Assertions.assertTrue(TypeValidator.isFloat(new BigDecimal(Float.MIN_VALUE)));
        Assertions.assertFalse(TypeValidator.isFloat(new BigDecimal(Float.MIN_VALUE - 1)));
        Assertions.assertTrue(TypeValidator.isFloat(new BigDecimal(Float.MAX_VALUE)));
        Assertions.assertFalse(TypeValidator.isFloat(new BigDecimal(Float.MAX_VALUE + 1)));
        Assertions.assertTrue(TypeValidator.isFloat(new BigDecimal("-8787888.888888")));
        Assertions.assertTrue(TypeValidator.isFloat(new BigDecimal("878728887888.8")));
    }

    @Test
    void testIsDouble() {
        Assertions.assertTrue(TypeValidator.isDouble(new BigDecimal("0")));
        Assertions.assertTrue(TypeValidator.isDouble(new BigDecimal("0.00")));
        Assertions.assertTrue(TypeValidator.isDouble(new BigDecimal("5.05")));
        Assertions.assertTrue(TypeValidator.isDouble(new BigDecimal("4.0000")));
        Assertions.assertTrue(TypeValidator.isDouble(new BigDecimal("0.05")));
        Assertions.assertTrue(TypeValidator.isDouble(new BigDecimal("-0")));
        Assertions.assertTrue(TypeValidator.isDouble(new BigDecimal(Double.MIN_VALUE)));
        Assertions.assertFalse(TypeValidator.isDouble(new BigDecimal(Double.MIN_VALUE - 1)));
        Assertions.assertTrue(TypeValidator.isDouble(new BigDecimal(Double.MAX_VALUE)));
        Assertions.assertFalse(TypeValidator.isDouble(new BigDecimal(Double.MAX_VALUE + 1)));
        Assertions.assertTrue(TypeValidator.isDouble(new BigDecimal("-8787888888888")));
        Assertions.assertTrue(TypeValidator.isDouble(new BigDecimal("8787288878888")));
    }
}
