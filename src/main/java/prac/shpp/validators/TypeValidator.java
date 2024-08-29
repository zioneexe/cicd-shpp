package prac.shpp.validators;

import java.math.BigDecimal;

public class TypeValidator {

    private TypeValidator() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isByte(BigDecimal number) {
        return isInteger(number) && isWithinByteRange(number);
    }

    public static boolean isShort(BigDecimal number) {
        return isInteger(number) && isWithinShortRange(number);
    }

    public static boolean isInt(BigDecimal number) {
        return isInteger(number) && isWithinIntRange(number);
    }

    public static boolean isLong(BigDecimal number) {
        return isInteger(number) && isWithinLongRange(number);
    }

    public static boolean isFloat(BigDecimal number) {
        return isWithinFloatRange(number);
    }

    public static boolean isDouble(BigDecimal number) {
        return isWithinDoubleRange(number);
    }

    public static boolean isWithinByteRange(BigDecimal number) {
        return number.compareTo(BigDecimal.valueOf(Byte.MIN_VALUE)) >= 0 &&
                number.compareTo(BigDecimal.valueOf(Byte.MAX_VALUE)) <= 0;
    }

    public static boolean isWithinShortRange(BigDecimal number) {
        return number.compareTo(BigDecimal.valueOf(Short.MIN_VALUE)) >= 0 &&
                number.compareTo(BigDecimal.valueOf(Short.MAX_VALUE)) <= 0;
    }

    public static boolean isWithinIntRange(BigDecimal number) {
        return number.compareTo(BigDecimal.valueOf(Integer.MIN_VALUE)) >= 0 &&
                number.compareTo(BigDecimal.valueOf(Integer.MAX_VALUE)) <= 0;
    }

    public static boolean isWithinLongRange(BigDecimal number) {
        return number.compareTo(BigDecimal.valueOf(Long.MIN_VALUE)) >= 0 &&
                number.compareTo(BigDecimal.valueOf(Long.MAX_VALUE)) <= 0;
    }

    public static boolean isWithinFloatRange(BigDecimal number) {
        return number.compareTo(BigDecimal.valueOf(-Float.MAX_VALUE)) >= 0 &&
                number.compareTo(BigDecimal.valueOf(Float.MAX_VALUE)) <= 0;
    }

    public static boolean isWithinDoubleRange(BigDecimal number) {
        return number.compareTo(BigDecimal.valueOf(-Double.MAX_VALUE)) >= 0 &&
                number.compareTo(BigDecimal.valueOf(Double.MAX_VALUE)) <= 0;
    }

    public static boolean isInteger(BigDecimal number) {
        return number.signum() == 0 || number.scale() <= 0 || number.stripTrailingZeros().scale() <= 0;
    }
}
