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
import prac.shpp.pojo.CalculationProperties;
import prac.shpp.enums.NumberType;
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
            mockValidator.when(() -> PropertiesValidator.validate(any(CalculationProperties.class), any(NumberType.class))).thenReturn(false);

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
            mockValidator.when(() -> PropertiesValidator.validate(any(CalculationProperties.class), any(NumberType.class))).thenReturn(true);

            App.main(new String[]{});

            verify(mockAppender, times(10)).doAppend(captorLoggingEvent.capture());

            List<LoggingEvent> loggingEvents = captorLoggingEvent.getAllValues();
            LoggingEvent errorLoggingEvent = loggingEvents.get(9);

            Assertions.assertEquals("Table printed.", errorLoggingEvent.getFormattedMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
