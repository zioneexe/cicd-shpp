package printers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prac.shpp.calculation.CalculationModule;
import prac.shpp.pojo.CalculationProperties;
import prac.shpp.pojo.Table;
import prac.shpp.enums.NumberType;
import prac.shpp.printers.TablePrinter;

class TestTablePrinter {

    @Test
    void testTablePrinter() {

        String expectedOutput =
                "\n+--------------+--------------+--------------+\n" +
                "|              |             1|             2|\n" +
                "+--------------+--------------+--------------+\n" +
                "|             1|             1|             2|\n" +
                "+--------------+--------------+--------------+\n" +
                "|             2|             2|             4|\n" +
                "+--------------+--------------+--------------+\n";

        CalculationProperties properties = new CalculationProperties("1", "2", "1");
        CalculationModule calculation = new CalculationModule(properties, NumberType.BYTE);
        Table table = calculation.createAndProcessTable();
        TablePrinter.printTable(table);

        Assertions.assertEquals(expectedOutput, TablePrinter.getPrintedTable());

    }
}
