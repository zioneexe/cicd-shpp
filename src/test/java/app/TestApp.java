package app;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;
import prac.shpp.App;
import prac.shpp.calculation.CalculationModule;
import prac.shpp.dtos.PropertiesDTO;
import prac.shpp.entities.Table;
import prac.shpp.enums.NumberType;
import prac.shpp.extractors.PropertiesExtractor;
import prac.shpp.printers.TablePrinter;
import prac.shpp.validators.PropertiesValidator;

import java.io.IOException;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestApp {

    @Mock
    private Appender<ILoggingEvent> mockAppender;

    @Captor
    private ArgumentCaptor<LoggingEvent> captorLoggingEvent;

    @BeforeEach
    void setUp() {
        final ch.qos.logback.classic.Logger logger =
                (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.example.main");
        logger.addAppender(mockAppender);
    }

    @AfterEach
    void tearDown() {
        final ch.qos.logback.classic.Logger logger =
                (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.example.main");
        logger.detachAppender(mockAppender);
    }

    @Test
    void testIfPropsNotValidated() {
        try (var mockValidator = mockStatic(PropertiesValidator.class)) {
            mockValidator.when(() -> PropertiesValidator.validate(any(PropertiesDTO.class), any(NumberType.class))).thenReturn(false);

            App.main(new String[]{});

            verify(mockAppender, times(6)).doAppend(captorLoggingEvent.capture());

            List<LoggingEvent> loggingEvents = captorLoggingEvent.getAllValues();
            LoggingEvent errorLoggingEvent = loggingEvents.get(5);

            Assertions.assertEquals("Invalid input in properties file. Not matching passed number type (INT).", errorLoggingEvent.getFormattedMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testIfPropsValidated() {
        try (var mockValidator = mockStatic(PropertiesValidator.class)) {
            mockValidator.when(() -> PropertiesValidator.validate(any(PropertiesDTO.class), any(NumberType.class))).thenReturn(true);

            App.main(new String[]{});

            verify(mockAppender, times(11)).doAppend(captorLoggingEvent.capture());

            List<LoggingEvent> loggingEvents = captorLoggingEvent.getAllValues();
            LoggingEvent errorLoggingEvent = loggingEvents.get(10);

            Assertions.assertEquals("Table printed.", errorLoggingEvent.getFormattedMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testMainExecutionFlow() throws IOException {
        PropertiesDTO mockProperties = mock(PropertiesDTO.class);
        NumberType mockNumberType = NumberType.INT;
        Table mockTable = mock(Table.class);

        CalculationModule mockCalculator = mock(CalculationModule.class);
        when(mockCalculator.createAndProcessTable()).thenReturn(mockTable);

        when(PropertiesExtractor.extractNumberType()).thenReturn(mockNumberType);
        when(PropertiesExtractor.extractNumberProperties(App.FILENAME)).thenReturn(mockProperties);
        when(PropertiesValidator.validate(mockProperties, mockNumberType)).thenReturn(true);

        App.main(new String[]{});

        Mockito.verify(mockCalculator).createAndProcessTable();

        try (MockedStatic<TablePrinter> mockPrinter = mockStatic(TablePrinter.class)) {
            mockPrinter.verify(() -> TablePrinter.printTable(mockTable));
        }
    }
}
