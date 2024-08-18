package prac.shpp.calculation;

import prac.shpp.dtos.PropertiesDTO;
import prac.shpp.entities.Table;
import prac.shpp.enums.NumberType;
import prac.shpp.services.NumberTypeProcessor;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class CalculationModule {

    public static final int PRECISION = 6;

    public static final MathContext mathContext = new MathContext(PRECISION, RoundingMode.HALF_UP);

    public static Table calculateTable(PropertiesDTO properties, NumberType numberType) {
        List<BigDecimal> tableHeader = new ArrayList<>();
        List<List<BigDecimal>> numbersTable = new ArrayList<>();

        BigDecimal minimumNumber = new BigDecimal(properties.getMinimumNumber(), mathContext);
        BigDecimal maximumNumber = new BigDecimal(properties.getMaximumNumber(), mathContext);
        BigDecimal step = new BigDecimal(properties.getStep(), mathContext);

        BigDecimal rowValue = new BigDecimal(minimumNumber.toString());
        while (rowValue.compareTo(maximumNumber) < 1) {
            List<BigDecimal> currentRow = new ArrayList<>();

            BigDecimal columnValue = new BigDecimal(minimumNumber.toString(), mathContext);

            while (columnValue.compareTo(maximumNumber) < 1) {
                BigDecimal resultValue = rowValue.multiply(columnValue);

                currentRow.add(resultValue);

                columnValue = columnValue.add(step);
            }

            tableHeader.add(rowValue);
            numbersTable.add(currentRow);

            rowValue = rowValue.add(step);
        }

        Table table = Table.builder().header(tableHeader).numberTable(numbersTable).build();
        NumberTypeProcessor.processTable(table, numberType);

        return table;
    }
}
