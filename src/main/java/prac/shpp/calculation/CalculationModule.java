package prac.shpp.calculation;

import prac.shpp.pojo.CalculationProperties;
import prac.shpp.pojo.Table;
import prac.shpp.enums.NumberType;
import prac.shpp.processors.NumberTypeProcessor;

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

    private boolean reverse = false;

    public CalculationModule(CalculationProperties properties, NumberType numberType) {
        this.minimumNumber = new BigDecimal(properties.getMinimumNumber(), mathContext);
        this.maximumNumber = new BigDecimal(properties.getMaximumNumber(), mathContext);
        this.step = new BigDecimal(properties.getStep(), mathContext);

        this.numberType = numberType;

        if (step.compareTo(BigDecimal.ZERO) < 0) reverse = true;
    }

    public Table createAndProcessTable() {
        Map.Entry<List<BigDecimal>, List<List<BigDecimal>>> tableData = calculateTable();
        List<BigDecimal> tableHeader = tableData.getKey();
        List<List<BigDecimal>> numbersTable = tableData.getValue();

        return Table.builder().header(tableHeader).numberTable(numbersTable).build();
    }

    private Map.Entry<List<BigDecimal>, List<List<BigDecimal>>> calculateTable() {
        List<BigDecimal> tableHeader = new ArrayList<>();
        List<List<BigDecimal>> numbersTable = new ArrayList<>();

        BigDecimal rowValue = new BigDecimal(reverse ? maximumNumber.toString() : minimumNumber.toString());

        while (reverse ? rowValue.compareTo(minimumNumber) > -1 : rowValue.compareTo(maximumNumber) < 1) {
            List<BigDecimal> currentRow = calculateRow(rowValue);

            BigDecimal convertedRowValue = NumberTypeProcessor.processNumber(rowValue, numberType);

            tableHeader.add(convertedRowValue);
            numbersTable.add(currentRow);

            rowValue = rowValue.add(step);
        }

        return new AbstractMap.SimpleEntry<>(tableHeader, numbersTable);
    }

    private List<BigDecimal> calculateRow(BigDecimal rowValue) {
        List<BigDecimal> currentRow = new ArrayList<>();

        BigDecimal columnValue = new BigDecimal(reverse ? maximumNumber.toString() : minimumNumber.toString());

        while (reverse ? columnValue.compareTo(minimumNumber) > -1 : columnValue.compareTo(maximumNumber) < 1) {
            BigDecimal result = rowValue.multiply(columnValue);
            BigDecimal convertedResult = NumberTypeProcessor.processNumber(result, numberType);

            currentRow.add(convertedResult);

            columnValue = columnValue.add(step);
        }

        return currentRow;
    }
}
