package prac.shpp.calculation;

import prac.shpp.dtos.PropertiesDTO;
import prac.shpp.entities.Table;
import prac.shpp.enums.NumberType;
import prac.shpp.services.NumberTypeProcessor;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CalculationModule {

    public static final int PRECISION = 6;

    public static final MathContext mathContext = new MathContext(PRECISION, RoundingMode.HALF_UP);

    private final BigDecimal minimumNumber;

    private final BigDecimal maximumNumber;

    private final BigDecimal step;

    private final NumberType numberType;

    public CalculationModule(PropertiesDTO properties, NumberType numberType) {
        this.minimumNumber = new BigDecimal(properties.getMinimumNumber(), mathContext);
        this.maximumNumber = new BigDecimal(properties.getMaximumNumber(), mathContext);
        this.step = new BigDecimal(properties.getStep(), mathContext);

        this.numberType = numberType;
    }

    public Table createAndProcessTable() {
        Map.Entry<List<BigDecimal>, List<List<BigDecimal>>> tableData = calculateTable();
        List<BigDecimal> tableHeader = tableData.getKey();
        List<List<BigDecimal>> numbersTable = tableData.getValue();

        Table table = Table.builder().header(tableHeader).numberTable(numbersTable).build();
        NumberTypeProcessor.processTable(table, numberType);

        return table;
    }

    private Map.Entry<List<BigDecimal>, List<List<BigDecimal>>> calculateTable() {
        List<BigDecimal> tableHeader = new ArrayList<>();
        List<List<BigDecimal>> numbersTable = new ArrayList<>();

        BigDecimal rowValue = new BigDecimal(minimumNumber.toString());
        while (rowValue.compareTo(maximumNumber) < 1) {
            List<BigDecimal> currentRow = calculateRow(rowValue);

            tableHeader.add(rowValue);
            numbersTable.add(currentRow);

            rowValue = rowValue.add(step);
        }

        return new AbstractMap.SimpleEntry<>(tableHeader, numbersTable);
    }

    private List<BigDecimal> calculateRow(BigDecimal rowValue) {
        List<BigDecimal> currentRow = new ArrayList<>();

        BigDecimal columnValue = new BigDecimal(minimumNumber.toString(), mathContext);

        while (columnValue.compareTo(maximumNumber) < 1) {
            BigDecimal resultValue = rowValue.multiply(columnValue);

            currentRow.add(resultValue);

            columnValue = columnValue.add(step);
        }

        return currentRow;
    }
}
