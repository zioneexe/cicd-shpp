import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class BigDecimalTest {

    @Test
    @DisplayName("Casting test")
    void testCasting() {
        BigDecimal number = new BigDecimal("1250.3397870");

        Assertions.assertEquals(1250, number.intValue());
    }

    @Test
    @DisplayName("Casting test")
    void testByteCasting() {
        BigDecimal number = new BigDecimal("1250.3397870");

        //(1250, number.byteValue());
        Assertions.assertEquals(1250, number.shortValue());
    }


    @Disabled
    @Test
    @DisplayName("Trailing zeros")
    void testTrailingZeros() {
        BigDecimal number = new BigDecimal("1020000000");

        Assertions.assertEquals(1020000000, number.stripTrailingZeros());
    }
}
