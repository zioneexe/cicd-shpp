package prac.shpp.validators;

import prac.shpp.pojo.Properties;
import prac.shpp.enums.NumberType;

import java.math.BigDecimal;

import static prac.shpp.App.LOGGER;
import static prac.shpp.calculation.CalculationModule.mathContext;

public class PropertiesValidator {

    private PropertiesValidator() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean validate(Properties properties, NumberType numberType) {
        LOGGER.debug("Properties validation called.");

        BigDecimal minimumNumber = new BigDecimal(properties.getMinimumNumber(), mathContext);
        BigDecimal maximumNumber = new BigDecimal(properties.getMaximumNumber(), mathContext);
        BigDecimal step = new BigDecimal(properties.getStep(), mathContext);

        boolean noOverflow = OverflowValidator.validate(minimumNumber, numberType) &&
                OverflowValidator.validate(maximumNumber, numberType) &&
                OverflowValidator.validate(step, numberType);

        return noOverflow && step.compareTo(BigDecimal.ZERO) > 0 && maximumNumber.compareTo(minimumNumber) > 0;
    }
}
