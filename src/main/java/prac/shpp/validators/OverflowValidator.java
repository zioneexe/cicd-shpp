package prac.shpp.validators;

import prac.shpp.enums.NumberType;

import java.math.BigDecimal;

import static prac.shpp.App.LOGGER;
import static prac.shpp.validators.TypeValidator.*;

public class OverflowValidator {

    private OverflowValidator() {
        throw new IllegalStateException("Utility class");
    }

    public static final BigDecimal OVERFLOW = BigDecimal.valueOf(Double.MAX_VALUE);

    public static boolean checkIfOverflowed(BigDecimal multiplicationResult) {
        return multiplicationResult.equals(OVERFLOW);
    }

    public static BigDecimal markIfOverflowed(BigDecimal multiplicationResult, NumberType numberType) {
        if (!validateRange(multiplicationResult, numberType)) {
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
        }

        return false;
    }

    public static boolean validateRange(BigDecimal number, NumberType numberType) {
        switch (numberType) {
            case BYTE:
                return isWithinByteRange(number);
            case SHORT:
                return isWithinShortRange(number);
            case INT:
                return isWithinIntRange(number);
            case LONG:
                return isWithinLongRange(number);
            case FLOAT:
                return isWithinFloatRange(number);
            case DOUBLE:
                return isWithinDoubleRange(number);
        }

        return false;
    }
}
