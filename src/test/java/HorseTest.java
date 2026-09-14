import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    @Test
    @DisplayName("constructor should throw exception when name is null")
    void constructorShouldThrowException_WhenNameIsNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, 2.0, 3.0)
        );

        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @Test
    @DisplayName("constructor should throw exception when speed is negative")
    void constructorShouldThrowException_WhenSpeedIsNegative() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Zorro", -2.0, 3.0)
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    @DisplayName("constructor should throw exception when distance is negative")
    void constructorShouldThrowException_WhenDistanceIsNegative() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Zorro", 2.0, -3.0)
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   ", "\t", "\n"})
    @DisplayName("constructor should throw exception when name is blank")
    void constructorShouldThrowException_WhenNameIsBlank(String blankName) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(blankName, 2.0, 3.0)
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    @DisplayName("getName should return name passed to constructor")
    void getName_ShouldReturnNamePassedToConstructor() {
        Horse horse = new Horse("Zorro", 2.0, 3.0);
        assertEquals("Zorro", horse.getName());
    }

    @Test
    @DisplayName("getSpeed should return speed passed to constructor")
    void getSpeedShouldReturnSpeedPassedToConstructor() {
        Horse horse = new Horse("Zorro", 2.0, 3.0);
        assertEquals(2.0, horse.getSpeed());
    }

    @Test
    @DisplayName("getDistance should return distance to constructor")
    void getDistance_ShouldReturnDistanceToConstructor() {
        Horse horse = new Horse("Zorro", 2.0, 3.0);
        assertEquals(3.0, horse.getDistance());
    }

    @Test
    @DisplayName("getDistance should return zero when created with two-arg constructor")
    void getDistance_ShouldReturnZero_WhenCreatedWithTwoArgConstructor() {
        Horse horse = new Horse("Zorro", 2.0);
        assertEquals(0, horse.getDistance());
    }

    @Test
    @DisplayName("move should call getRandomDouble")
    void move_ShouldCallGetRandomDouble() {
       Horse horse = new Horse("Zorro", 2.0, 3.0);
       try(MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
           horse.move();
           mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
       }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.5, 0.9})
    @DisplayName("move should update distance by formula distance + speed * randomDouble")
    void move_ShouldUpdateDistanceByFormula(double randomValue) {
        Horse horse = new Horse("Zorro", 2.0, 3.0);

        try(MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomValue);

            horse.move();
            double expectedDistance = 3.0 + 2.0 * randomValue;
            assertEquals(expectedDistance, horse.getDistance());
        }
    }
}