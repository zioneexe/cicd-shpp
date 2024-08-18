package prac.shpp.validators;

import prac.shpp.enums.NumberType;

import java.math.BigDecimal;

import static prac.shpp.App.LOGGER;
import static prac.shpp.validators.TypeValidator.*;

public class OverflowValidator {

    public static final BigDecimal OVERFLOW = BigDecimal.valueOf(Double.MAX_VALUE);

    public static boolean checkIfOverflowed(BigDecimal multiplicationResult) {
        return multiplicationResult.equals(OVERFLOW);
    }

    public static BigDecimal markIfOverflowed(BigDecimal multiplicationResult, NumberType numberType) {
        if (!validate(multiplicationResult, numberType)) {
            LOGGER.trace("A number marked OVERFLOW");

            return OVERFLOW;
        }

        return multiplicationResult;
    }

    public static boolean validate(BigDecimal number, NumberType numberType) {
        switch (numberType) {
            case BYTE:
                return isByte(number);
            case SHORT:
                return isShort(number);
            case INT:
                return isInt(number);
            case LONG:
                return isLong(number);
            case FLOAT:
                return isFloat(number);
            case DOUBLE:
                return isDouble(number);
            default:
                throw new IllegalStateException("Unexpected value: " + numberType);
        }
    }
}
