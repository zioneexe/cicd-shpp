package prac.shpp.services;

import prac.shpp.entities.Table;
import prac.shpp.enums.NumberType;
import prac.shpp.validators.OverflowValidator;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static prac.shpp.App.LOGGER;
import static prac.shpp.calculation.CalculationModule.PRECISION;
import static prac.shpp.calculation.CalculationModule.mathContext;
import static prac.shpp.validators.OverflowValidator.checkIfOverflowed;

public class NumberTypeProcessor {

    public static void processTable(Table table, NumberType numberType) {
        LOGGER.debug("Processing table numbers.");

        List<BigDecimal> header = table.getHeader();
        List<List<BigDecimal>> numberTable = table.getNumberTable();

        List<BigDecimal> validatedHeader = processHeader(header, numberType);
        List<List<BigDecimal>> validatedNumberTable = processNumberTable(numberTable, numberType);

        table.setHeader(validatedHeader);
        table.setNumberTable(validatedNumberTable);
    }

    protected static BigDecimal convertNumber(BigDecimal number, NumberType numberType) {
        if (checkIfOverflowed(number)) return number;

        switch (numberType) {
            case BYTE:
                return BigDecimal.valueOf(number.byteValue());
            case SHORT:
                return BigDecimal.valueOf(number.shortValue());
            case INT:
                return BigDecimal.valueOf(number.intValue());
            case LONG:
                return BigDecimal.valueOf(number.longValue());
            case FLOAT:
                return BigDecimal.valueOf(number.floatValue());
            case DOUBLE:
                return BigDecimal.valueOf(number.doubleValue());
            default:
                throw new IllegalStateException("Unexpected value: " + numberType);
        }
    }

    private static List<BigDecimal> processHeader(List<BigDecimal> header, NumberType numberType) {
        List<BigDecimal> validatedHeader = header.stream()
                .map(number -> {
                    BigDecimal markedNumber = OverflowValidator.markIfOverflowed(number, numberType);
                    BigDecimal convertedNumber = convertNumber(markedNumber, numberType);

                    return round(convertedNumber);
                })
                .collect(Collectors.toList());
        LOGGER.trace("Converted and rounded header numbers.");

        return validatedHeader;
    }

    private static List<List<BigDecimal>> processNumberTable(List<List<BigDecimal>> numberTable, NumberType numberType) {
        List<List<BigDecimal>> validatedNumberTable = numberTable.stream()
                .map(row -> row.stream()
                        .map(number -> {
                            BigDecimal markedNumber = OverflowValidator.markIfOverflowed(number, numberType);
                            BigDecimal convertedNumber = convertNumber(markedNumber, numberType);

                            return round(convertedNumber);
                        })
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
        LOGGER.trace("Converted and rounded table numbers.");

        return validatedNumberTable;
    }

    protected static BigDecimal round(BigDecimal number) {
        if (checkIfOverflowed(number)) return number;

        if (number.scale() > PRECISION) {
            number = number.round(mathContext);
        }

        return number.stripTrailingZeros();
    }
}
