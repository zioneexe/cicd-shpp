package validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import prac.shpp.validators.TypeValidator;

import java.math.BigDecimal;

class TestTypeValidator {

    @Test
    void testByteZeroValues() {
        BigDecimal number1 = new BigDecimal("0");
        BigDecimal number2 = new BigDecimal("0.00");
        BigDecimal number3 = new BigDecimal("-0");

        Assertions.assertTrue(TypeValidator.isByte(number1));
        Assertions.assertTrue(TypeValidator.isByte(number2));
        Assertions.assertTrue(TypeValidator.isByte(number3));
    }

    @Test
    void testShortZeroValues() {
        BigDecimal number1 = new BigDecimal("0");
        BigDecimal number2 = new BigDecimal("0.00");
        BigDecimal number3 = new BigDecimal("-0");

        Assertions.assertTrue(TypeValidator.isShort(number1));
        Assertions.assertTrue(TypeValidator.isShort(number2));
        Assertions.assertTrue(TypeValidator.isShort(number3));
    }

    @Test
    void testIntZeroValues() {
        BigDecimal number1 = new BigDecimal("0");
        BigDecimal number2 = new BigDecimal("0.00");
        BigDecimal number3 = new BigDecimal("-0");

        Assertions.assertTrue(TypeValidator.isInt(number1));
        Assertions.assertTrue(TypeValidator.isInt(number2));
        Assertions.assertTrue(TypeValidator.isInt(number3));
    }

    @Test
    void testLongZeroValues() {
        BigDecimal number1 = new BigDecimal("0");
        BigDecimal number2 = new BigDecimal("0.00");
        BigDecimal number3 = new BigDecimal("-0");

        Assertions.assertTrue(TypeValidator.isLong(number1));
        Assertions.assertTrue(TypeValidator.isLong(number2));
        Assertions.assertTrue(TypeValidator.isLong(number3));
    }

    @Test
    void testFloatZeroValues() {
        BigDecimal number1 = new BigDecimal("0");
        BigDecimal number2 = new BigDecimal("0.00");
        BigDecimal number3 = new BigDecimal("-0");

        Assertions.assertTrue(TypeValidator.isFloat(number1));
        Assertions.assertTrue(TypeValidator.isFloat(number2));
        Assertions.assertTrue(TypeValidator.isFloat(number3));
    }

    @Test
    void testDoubleZeroValues() {
        BigDecimal number1 = new BigDecimal("0");
        BigDecimal number2 = new BigDecimal("0.00");
        BigDecimal number3 = new BigDecimal("-0");

        Assertions.assertTrue(TypeValidator.isDouble(number1));
        Assertions.assertTrue(TypeValidator.isDouble(number2));
        Assertions.assertTrue(TypeValidator.isDouble(number3));
    }

    @Test
    void testIsByte() {
        Assertions.assertFalse(TypeValidator.isByte(new BigDecimal("0.05")));
        Assertions.assertTrue(TypeValidator.isByte(new BigDecimal(Byte.MIN_VALUE)));
        Assertions.assertFalse(TypeValidator.isByte(new BigDecimal(Byte.MIN_VALUE).subtract(BigDecimal.ONE)));
        Assertions.assertTrue(TypeValidator.isByte(new BigDecimal(Byte.MAX_VALUE)));
        Assertions.assertFalse(TypeValidator.isByte(new BigDecimal(Byte.MAX_VALUE).add(BigDecimal.ONE)));
        Assertions.assertFalse(TypeValidator.isByte(new BigDecimal("5000")));
        Assertions.assertFalse(TypeValidator.isByte(new BigDecimal("-400")));
    }

    @Test
    void testIsShort() {
        Assertions.assertFalse(TypeValidator.isShort(new BigDecimal("0.05")));
        Assertions.assertTrue(TypeValidator.isShort(new BigDecimal(Short.MIN_VALUE)));
        Assertions.assertFalse(TypeValidator.isShort(new BigDecimal(Short.MIN_VALUE).subtract(BigDecimal.ONE)));
        Assertions.assertTrue(TypeValidator.isShort(new BigDecimal(Short.MAX_VALUE)));
        Assertions.assertFalse(TypeValidator.isShort(new BigDecimal(Short.MAX_VALUE).add(BigDecimal.ONE)));
        Assertions.assertFalse(TypeValidator.isShort(new BigDecimal("500000000")));
        Assertions.assertFalse(TypeValidator.isShort(new BigDecimal("-40000")));
    }

    @Test
    void testIsInt() {
        Assertions.assertFalse(TypeValidator.isInt(new BigDecimal("0.05")));
        Assertions.assertTrue(TypeValidator.isInt(new BigDecimal(Integer.MIN_VALUE)));
        Assertions.assertFalse(TypeValidator.isInt(new BigDecimal("-2147483649")));
        Assertions.assertTrue(TypeValidator.isInt(new BigDecimal(Integer.MAX_VALUE)));
        Assertions.assertFalse(TypeValidator.isInt(new BigDecimal("2147483648")));
        Assertions.assertFalse(TypeValidator.isInt(new BigDecimal("-8787888888888")));
        Assertions.assertFalse(TypeValidator.isInt(new BigDecimal("8787288878888")));
    }

    @Test
    void testIsLong() {
        Assertions.assertFalse(TypeValidator.isLong(new BigDecimal("0.05")));
        Assertions.assertTrue(TypeValidator.isLong(new BigDecimal(Long.MIN_VALUE)));
        Assertions.assertFalse(TypeValidator.isLong(new BigDecimal("-9223372036854775809")));
        Assertions.assertTrue(TypeValidator.isLong(new BigDecimal(Long.MAX_VALUE)));
        Assertions.assertFalse(TypeValidator.isLong(new BigDecimal("9223372036854775808")));
        Assertions.assertTrue(TypeValidator.isLong(new BigDecimal("-8787888888888")));
        Assertions.assertFalse(TypeValidator.isLong(new BigDecimal("878728887888800000000000000000000")));
    }

    @Test
    void testIsFloat() {
        Assertions.assertTrue(TypeValidator.isFloat(new BigDecimal("0.05")));
        Assertions.assertTrue(TypeValidator.isFloat(new BigDecimal("5.05")));
        Assertions.assertTrue(TypeValidator.isFloat(new BigDecimal("4.0000")));
        Assertions.assertTrue(TypeValidator.isFloat(BigDecimal.valueOf(-Float.MAX_VALUE)));
        Assertions.assertFalse(TypeValidator.isFloat(BigDecimal.valueOf(-Float.MAX_VALUE).subtract(BigDecimal.ONE)));
        Assertions.assertTrue(TypeValidator.isFloat(new BigDecimal(Float.MAX_VALUE).subtract(BigDecimal.ZERO)));
        Assertions.assertFalse(TypeValidator.isFloat(new BigDecimal(Float.MAX_VALUE).subtract(BigDecimal.ONE)));
        Assertions.assertTrue(TypeValidator.isFloat(new BigDecimal("-8787888.888888")));
        Assertions.assertTrue(TypeValidator.isFloat(new BigDecimal("878728887888.8")));
    }

    @Test
    void testIsDouble() {
        Assertions.assertTrue(TypeValidator.isDouble(new BigDecimal("5.05")));
        Assertions.assertTrue(TypeValidator.isDouble(new BigDecimal("4.0000")));
        Assertions.assertTrue(TypeValidator.isDouble(new BigDecimal("0.05")));
        Assertions.assertTrue(TypeValidator.isDouble(BigDecimal.valueOf(-Double.MAX_VALUE)));
        Assertions.assertFalse(TypeValidator.isDouble(BigDecimal.valueOf(-Double.MAX_VALUE).subtract(BigDecimal.ONE)));
        Assertions.assertFalse(TypeValidator.isDouble(new BigDecimal(Double.MAX_VALUE).subtract(BigDecimal.TEN))); // ???
        Assertions.assertFalse(TypeValidator.isDouble(new BigDecimal(Double.MAX_VALUE).add(BigDecimal.ONE)));
        Assertions.assertTrue(TypeValidator.isDouble(new BigDecimal("-8787888888888")));
        Assertions.assertTrue(TypeValidator.isDouble(new BigDecimal("8787288878888")));
    }
}
