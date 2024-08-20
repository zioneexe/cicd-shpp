package prac.shpp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import prac.shpp.calculation.CalculationModule;
import prac.shpp.dtos.PropertiesDTO;
import prac.shpp.entities.Table;
import prac.shpp.enums.NumberType;
import prac.shpp.extractors.PropertiesExtractor;
import prac.shpp.printers.TablePrinter;
import prac.shpp.validators.PropertiesValidator;

import java.io.IOException;

public class App {

    public static final Logger LOGGER = LoggerFactory.getLogger("com.example.main");

    public static void main(String[] args) throws IOException {
        LOGGER.info("App started.");
        LOGGER.info("Using default INT type.");

        NumberType numberType = PropertiesExtractor.extractNumberType();
        PropertiesDTO properties = PropertiesExtractor.extractNumberProperties();
        LOGGER.info("Extracted properties: {}", properties);

        if (!PropertiesValidator.validate(properties, numberType)) {
            LOGGER.error("Invalid input in properties file. Not matching passed number type ({}).", numberType);
            return;
        }

        CalculationModule calculator = new CalculationModule(properties, numberType);
        Table resultTable = calculator.createAndProcessTable();
        LOGGER.info("Table calculated. Passing to a printer.");

        TablePrinter.printTable(resultTable);
        LOGGER.info("Table printed.");
    }
}