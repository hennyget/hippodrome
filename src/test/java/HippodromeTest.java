import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    @DisplayName("constructor should throw exception when horses list is null")
    void constructor_ShouldThrowException_WhenHorsesListIsNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(null)
        );
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    @DisplayName("constructor should throw exception when horses list is empty")
    void constructor_ShouldThrowException_WhenHorsesListIsEmpty() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(List.of())
        );
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    @DisplayName("getHorses should return same list that was passed to constructor")
    void getHorses_shouldReturnHorsesPassedToConstructor() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse " + i , i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    @DisplayName("move should call move on every horse")
    void move_ShouldCallMoveOnEveryHorse() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for  (Horse horse : horses) {
            Mockito.verify(horse).move();
        }
    }

    @Test
    @DisplayName("getWinner should return horse with max distance")
    void getWinner_ShouldReturnHorseWithMaxDistance() {
        Horse horse1 = new Horse("Horse 1", 2.0, 5.0);
        Horse horse2 = new Horse("Horse 2", 2.0, 10.0);
        Horse horse3 = new Horse("Horse 3", 2.0, 3.0);

        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, horse3));
        assertEquals(horse2, hippodrome.getWinner());
    }
}