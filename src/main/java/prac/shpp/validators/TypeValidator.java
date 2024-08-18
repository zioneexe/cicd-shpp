package prac.shpp.validators;

import java.math.BigDecimal;

public class TypeValidator {

    public static boolean isByte(BigDecimal number) {
        return isNotDecimal(number) &&
                number.compareTo(BigDecimal.valueOf(Byte.MIN_VALUE)) >= 0 &&
                number.compareTo(BigDecimal.valueOf(Byte.MAX_VALUE)) <= 0;
    }

    public static boolean isShort(BigDecimal number) {
        return isNotDecimal(number) &&
                number.compareTo(BigDecimal.valueOf(Short.MIN_VALUE)) >= 0 &&
                number.compareTo(BigDecimal.valueOf(Short.MAX_VALUE)) <= 0;
    }

    public static boolean isInt(BigDecimal number) {
        return isNotDecimal(number) &&
                number.compareTo(BigDecimal.valueOf(Integer.MIN_VALUE)) >= 0 &&
                number.compareTo(BigDecimal.valueOf(Integer.MAX_VALUE)) <= 0;
    }

    public static boolean isLong(BigDecimal number) {
        return isNotDecimal(number) &&
                number.compareTo(BigDecimal.valueOf(Long.MIN_VALUE)) >= 0 &&
                number.compareTo(BigDecimal.valueOf(Long.MAX_VALUE)) <= 0;
    }

    public static boolean isFloat(BigDecimal number) {
        return isDecimal(number) &&
                number.compareTo(BigDecimal.valueOf(Float.MIN_VALUE)) >= 0 &&
                number.compareTo(BigDecimal.valueOf(Float.MAX_VALUE)) <= 0;
    }

    public static boolean isDouble(BigDecimal number) {
        return isDecimal(number) &&
                number.compareTo(BigDecimal.valueOf(Double.MIN_VALUE)) >= 0 &&
                number.compareTo(BigDecimal.valueOf(Double.MAX_VALUE)) <= 0;
    }

    private static boolean isDecimal(BigDecimal number) {
        return number.stripTrailingZeros().scale() >= 0;
    }

    private static boolean isNotDecimal(BigDecimal number) {
        return number.stripTrailingZeros().scale() <= 0;
    }
}
