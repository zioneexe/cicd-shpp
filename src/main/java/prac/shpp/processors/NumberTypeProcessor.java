package prac.shpp.processors;

import prac.shpp.enums.NumberType;
import prac.shpp.validators.OverflowValidator;

import java.math.BigDecimal;

import static prac.shpp.calculation.CalculationModule.PRECISION;
import static prac.shpp.calculation.CalculationModule.mathContext;
import static prac.shpp.validators.OverflowValidator.checkIfOverflowed;
import static prac.shpp.validators.TypeValidator.isInteger;

public class NumberTypeProcessor {

    public static BigDecimal processNumber(BigDecimal number, NumberType numberType) {
        BigDecimal markedNumber = OverflowValidator.markIfOverflowed(number, numberType);

        return round(convertNumber(markedNumber, numberType));
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
                return new BigDecimal(number.floatValue());
            case DOUBLE:
                return new BigDecimal(number.doubleValue());
            default:
                throw new IllegalStateException("Unexpected value: " + numberType);
        }
    }

    protected static BigDecimal round(BigDecimal number) {
        if (checkIfOverflowed(number)) return number;

        if (number.scale() > PRECISION) {
            number = number.round(mathContext);
        }

        return isInteger(number) ? number : number.stripTrailingZeros();
    }
}
