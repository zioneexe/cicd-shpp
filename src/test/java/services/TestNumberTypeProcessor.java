package services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import prac.shpp.entities.Table;
import prac.shpp.enums.NumberType;
import prac.shpp.services.NumberTypeProcessor;
import prac.shpp.validators.OverflowValidator;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static prac.shpp.validators.OverflowValidator.OVERFLOW;

class TestNumberTypeProcessor extends  NumberTypeProcessor {

    @Test
    void testProcessTable() {
        List<BigDecimal> header = List.of(new BigDecimal("123.456"), new BigDecimal("789.012"));
        List<List<BigDecimal>> numberTable = List.of(
                List.of(new BigDecimal("345.678"), new BigDecimal("901.234")),
                List.of(new BigDecimal("567.890"), new BigDecimal("123.456"))
        );

        Table table = Table.builder().build();
        table.setHeader(header);
        table.setNumberTable(numberTable);

        List<BigDecimal> expectedHeader = List.of(
                new BigDecimal("123").stripTrailingZeros(),
                new BigDecimal("789").stripTrailingZeros()
        );

        List<List<BigDecimal>> expectedTable = List.of(
                List.of(
                        new BigDecimal("345").stripTrailingZeros(),
                        new BigDecimal("901").stripTrailingZeros()
                ),
                List.of(
                        new BigDecimal("567").stripTrailingZeros(),
                        new BigDecimal("123").stripTrailingZeros()
                )
        );

        NumberTypeProcessor.processTable(table, NumberType.INT);

        Assertions.assertEquals(expectedHeader, table.getHeader());
        Assertions.assertEquals(expectedTable, table.getNumberTable());
    }

    @Test
    void testRoundingTrailingZeros() {
        BigDecimal result1 = TestNumberTypeProcessor.round(new BigDecimal("5.0"));
        BigDecimal result2 = TestNumberTypeProcessor.round(new BigDecimal("0.00"));
        BigDecimal result3 = TestNumberTypeProcessor.round(new BigDecimal("6.00000000"));

        BigDecimal expected1 = new BigDecimal("5");
        BigDecimal expected2 = new BigDecimal("0");
        BigDecimal expected3 = new BigDecimal("6");

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
        BigDecimal expected = BigDecimal.valueOf(input.floatValue());

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
