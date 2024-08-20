package other;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import prac.shpp.printers.TablePrinter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

class TestSmth {

    @Disabled
    @Test
    void testGetNumberDigits() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = TablePrinter.class.getDeclaredMethod("getNumberDigits", BigDecimal.class);

        method.setAccessible(true);

        TablePrinter tp = new TablePrinter();

        int result = (int) method.invoke(tp, new BigDecimal("1"));
        Assertions.assertEquals(1, result);

        result = (int) method.invoke(tp, new BigDecimal("18787"));
        Assertions.assertEquals(5, result);

        result = (int) method.invoke(tp, new BigDecimal("-6"));
        Assertions.assertEquals(2, result);

        result = (int) method.invoke(tp, new BigDecimal("89"));
        Assertions.assertEquals(2, result);

        result = (int) method.invoke(tp, new BigDecimal("0"));
        Assertions.assertEquals(1, result);

        result = (int) method.invoke(tp, new BigDecimal("0.0000"));
        Assertions.assertEquals(6, result);

        result = (int) method.invoke(tp, new BigDecimal("0.0001"));
        Assertions.assertEquals(6, result);


        result = (int) method.invoke(tp, new BigDecimal("0.01"));
        Assertions.assertEquals(4, result);
    }
}
