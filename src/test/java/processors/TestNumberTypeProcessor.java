package processors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import prac.shpp.pojo.Table;
import prac.shpp.enums.NumberType;
import prac.shpp.processors.NumberTypeProcessor;
import prac.shpp.validators.OverflowValidator;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static prac.shpp.validators.OverflowValidator.OVERFLOW;

class TestNumberTypeProcessor extends NumberTypeProcessor {

    @Test
    void testRoundingTrailingZeros() {
        BigDecimal result1 = TestNumberTypeProcessor.round(new BigDecimal("5.0"));
        BigDecimal result2 = TestNumberTypeProcessor.round(new BigDecimal("0.00"));
        BigDecimal result3 = TestNumberTypeProcessor.round(new BigDecimal("6.00600000"));

        BigDecimal expected1 = new BigDecimal("5.0");
        BigDecimal expected2 = new BigDecimal("0.00");
        BigDecimal expected3 = new BigDecimal("6.006");

        Assertions.assertEquals(expected1, result1);
        Assertions.assertEquals(expected2, result2);
        Assertions.assertEquals(expected3, result3);
    }

    @Test
    void testRoundingWithLargerScale() {
        BigDecimal result1 = TestNumberTypeProcessor.round(new BigDecimal("5.6688878787"));
        BigDecimal result2 = TestNumberTypeProcessor.round(new BigDecimal("-5.8948921"));

        BigDecimal expected1 = new BigDecimal("5.66889");
        BigDecimal expected2 = new BigDecimal("-5.89489");

        Assertions.assertEquals(expected1, result1);
        Assertions.assertEquals(expected2, result2);
    }

    @Test
    void testRoundingWithSmallerScale() {
        BigDecimal result = TestNumberTypeProcessor.round(new BigDecimal("3.29"));

        BigDecimal expected = new BigDecimal("3.29");

        Assertions.assertEquals(expected, result);
    }

    @Test
    void testRoundingWithEqualScale() {
        BigDecimal result = TestNumberTypeProcessor.round(new BigDecimal("-5.894896"));

        BigDecimal expected = new BigDecimal("-5.894896");

        Assertions.assertEquals(expected, result);
    }

    @Test
    void testRoundingWithOverflow() {
        BigDecimal result = TestNumberTypeProcessor.round(OVERFLOW);

        Assertions.assertEquals(OVERFLOW, result);
    }

    @Test
    void testConvertNumberWithOverflow() {
        BigDecimal input = new BigDecimal("99999999999999.999");

        try (MockedStatic<OverflowValidator> mocked = mockStatic(OverflowValidator.class)) {
            mocked.when(() -> OverflowValidator.checkIfOverflowed(any(BigDecimal.class))).thenReturn(true);

            BigDecimal result = TestNumberTypeProcessor.convertNumber(input, NumberType.INT);
            Assertions.assertEquals(input, result);
        }

    }

    @Test
    void testConvertNumberToByte() {
        BigDecimal input = new BigDecimal("127");
        BigDecimal expected = BigDecimal.valueOf(input.byteValue());

        BigDecimal result = convertNumber(input, NumberType.BYTE);

        Assertions.assertEquals(expected, result);
    }

    @Test
    void testConvertNumberToShort() {
        BigDecimal input = new BigDecimal("32767");
        BigDecimal expected = BigDecimal.valueOf(input.shortValue());

        BigDecimal result = convertNumber(input, NumberType.SHORT);

        Assertions.assertEquals(expected, result);
    }

    @Test
    void testConvertNumberToInt() {
        BigDecimal input = new BigDecimal("2147483647");
        BigDecimal expected = BigDecimal.valueOf(input.intValue());

        BigDecimal result = convertNumber(input, NumberType.INT);

        Assertions.assertEquals(expected, result);
    }

    @Test
    void testConvertNumberToLong() {
        BigDecimal input = new BigDecimal("9223372036854775807");
        BigDecimal expected = BigDecimal.valueOf(input.longValue());

        BigDecimal result = convertNumber(input, NumberType.LONG);

        Assertions.assertEquals(expected, result);
    }

    @Test
    void testConvertNumberToFloat() {
        BigDecimal input = new BigDecimal("3.4028235E38");
        BigDecimal expected = new BigDecimal("340282346638528859811704183484516925440");

        BigDecimal result = convertNumber(input, NumberType.FLOAT);

        Assertions.assertEquals(expected, result);
    }

    @Test
    void testConvertNumberToDouble() {
        BigDecimal input = new BigDecimal("1.7976931348623157E308");
        BigDecimal expected = BigDecimal.valueOf(input.doubleValue());

        BigDecimal result = convertNumber(input, NumberType.DOUBLE);

        Assertions.assertEquals(expected, result);
    }
}
