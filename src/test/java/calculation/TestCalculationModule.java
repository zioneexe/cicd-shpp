package calculation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import prac.shpp.calculation.CalculationModule;
import prac.shpp.pojo.Properties;
import prac.shpp.pojo.Table;
import prac.shpp.enums.NumberType;

import java.math.BigDecimal;
import java.util.List;

class TestCalculationModule {

    @Test
    void testValidTableCreation() {
        Properties properties = new Properties("1", "2", "1");
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
}
