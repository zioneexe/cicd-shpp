package prac.shpp.validators;

import prac.shpp.dtos.PropertiesDTO;
import prac.shpp.enums.NumberType;

import java.math.BigDecimal;

import static prac.shpp.App.LOGGER;
import static prac.shpp.calculation.CalculationModule.mathContext;

public class PropertiesValidator {

    public static boolean validate(PropertiesDTO properties, NumberType numberType) {
        LOGGER.debug("Properties validation called.");

        BigDecimal minimumNumber = new BigDecimal(properties.getMinimumNumber(), mathContext);
        BigDecimal maximumNumber = new BigDecimal(properties.getMaximumNumber(), mathContext);
        BigDecimal step = new BigDecimal(properties.getStep(), mathContext);

        return OverflowValidator.validate(minimumNumber, numberType) &&
                OverflowValidator.validate(maximumNumber, numberType) &&
                OverflowValidator.validate(step, numberType);
    }
}
