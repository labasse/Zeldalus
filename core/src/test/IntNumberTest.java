import com.syllab.games.utils.IntNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class IntNumberTest {
    @Test
    void isOddWithEvenValueReturnsFalse() {
        // Arrange
        // Act
        boolean actual = IntNumber.isOdd(4);
        // Assert
        //assertEquals(false, actual);
        assertFalse(actual);
    }
    @Test
    void isOddWithOddValueReturnsTrue() {
        assertTrue(IntNumber.isOdd(5));
    }
    @Test
    void isOddWithNegativeOddValueReturnsTrue() {
        assertTrue(IntNumber.isOdd(-5));
    }
    @Test
    void isOddWithNegativeEvenValueReturnsFalse() {
        assertFalse(IntNumber.isOdd(-6));
    }
    @Test
    void isOddWithZeroValueReturnsFalse() {
        assertFalse(IntNumber.isOdd(0));
    }

    @Test
    void isEvenWithEvenValueReturnsTrue() {
        assertTrue(IntNumber.isEven(4));
    }
    @Test
    void isEvenWithOddValueReturnsFalse() {
        assertFalse(IntNumber.isEven(5));
    }
    @Test
    void isEvenWithNegativeOddValueReturnsFalse() {
        assertFalse(IntNumber.isEven(-5));
    }
    @Test
    void isEvenWithNegativeEvenValueReturnsTrue() {
        assertTrue(IntNumber.isEven(-6));
    }
    @Test
    void isEvenWithZeroValueReturnsTrue() {
        assertTrue(IntNumber.isEven(0));
    }

    @Test
    void oppositeWithPositiveAndNonZeroBitReturnsNegative() {
        assertEquals(-7, IntNumber.opposite(7, 2));
    }
    @Test
    void oppositeWithPositiveAndZeroBitReturnsSameNumber() {
        assertEquals(7, IntNumber.opposite(7, 0));
    }
    @Test
    void oppositeWithNegativeAndNonZeroBitReturnsPositive() {
        assertEquals(7, IntNumber.opposite(-7, 2));
    }
    @Test
    void oppositeWithNegativeAndZeroBitReturnsSameNumber() {
        assertEquals(-7, IntNumber.opposite(-7, 0));
    }
    @Test
    void oppositeWithZeroAndNonZeroBitReturnsZero() {
        assertEquals(0, IntNumber.opposite(0, 2));
    }
}
