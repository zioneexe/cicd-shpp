package calculation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import prac.shpp.calculation.CalculationModule;
import prac.shpp.pojo.CalculationProperties;
import prac.shpp.pojo.Table;
import prac.shpp.enums.NumberType;

import java.math.BigDecimal;
import java.util.List;

class TestCalculationModule {

    @Test
    void testValidTableCreation() {
        CalculationProperties properties = new CalculationProperties("1", "2", "1");
        NumberType numberType = NumberType.BYTE;

        CalculationModule calculator = new CalculationModule(properties, numberType);
        Table table = calculator.createAndProcessTable();

        List<List<BigDecimal>> expectedNumberTable = List.of(
                List.of(BigDecimal.valueOf(1), BigDecimal.valueOf(2)),
                List.of(BigDecimal.valueOf(2), BigDecimal.valueOf(4))
        );
        List<BigDecimal> expectedHeader = List.of(BigDecimal.valueOf(1), BigDecimal.valueOf(2));

        Assertions.assertEquals(expectedNumberTable, table.getNumberTable());
        Assertions.assertEquals(expectedHeader, table.getHeader());
    }

    @Test
    void testTableCreationIfNegativeStep() {
        CalculationProperties properties = new CalculationProperties("2", "4", "-2");
        NumberType numberType = NumberType.BYTE;

        CalculationModule calculator = new CalculationModule(properties, numberType);
        Table table = calculator.createAndProcessTable();

        List<List<BigDecimal>> expectedNumberTable = List.of(
                List.of(BigDecimal.valueOf(16), BigDecimal.valueOf(8)),
                List.of(BigDecimal.valueOf(8), BigDecimal.valueOf(4))
        );
        List<BigDecimal> expectedHeader = List.of(BigDecimal.valueOf(4), BigDecimal.valueOf(2));

        Assertions.assertEquals(expectedNumberTable, table.getNumberTable());
        Assertions.assertEquals(expectedHeader, table.getHeader());
    }
}
