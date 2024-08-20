package services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import prac.shpp.entities.Table;
import prac.shpp.enums.NumberType;
import prac.shpp.services.NumberTypeProcessor;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

class TestNumberTypeProcessor {

    @Test
    void testProcessTable() {
        List<BigDecimal> header = Arrays.asList(new BigDecimal("123.456"), new BigDecimal("789.012"));
        List<List<BigDecimal>> numberTable = Arrays.asList(
                Arrays.asList(new BigDecimal("345.678"), new BigDecimal("901.234")),
                Arrays.asList(new BigDecimal("567.890"), new BigDecimal("123.456"))
        );

        Table table = Table.builder().build();
        table.setHeader(header);
        table.setNumberTable(numberTable);

        List<BigDecimal> expectedHeader = Arrays.asList(
                new BigDecimal("123").stripTrailingZeros(),
                new BigDecimal("789").stripTrailingZeros()
        );

        List<List<BigDecimal>> expectedTable = Arrays.asList(
                Arrays.asList(
                        new BigDecimal("345").stripTrailingZeros(),
                        new BigDecimal("901").stripTrailingZeros()
                ),
                Arrays.asList(
                        new BigDecimal("567").stripTrailingZeros(),
                        new BigDecimal("123").stripTrailingZeros()
                )
        );

        NumberTypeProcessor.processTable(table, NumberType.INT);

        Assertions.assertEquals(expectedHeader, table.getHeader());
        Assertions.assertEquals(expectedTable, table.getNumberTable());
    }
}
