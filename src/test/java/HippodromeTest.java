import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {
    @Test
    void constructor_NullHorsesParamPassed_ThrowsIllegalArgumentException() {
        List<Horse> horses = null;

        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
    }

    @Test
    void constructor_NullHorsesParamPassed_ThrowsIllegalArgumentException_ReturnMessage() {
        String expectedMessage = "Horses cannot be null.";
        List<Horse> horses = null;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void constructor_EmptyHorsesParamPassed_ThrowsIllegalArgumentException() {
        List<Horse> horses = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
    }

    @Test
    void constructor_EmptyHorsesParamPassed_ThrowsIllegalArgumentException_ReturnMessage() {
        String expectedMessage = "Horses cannot be empty.";
        List<Horse> horses = new ArrayList<>();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getHorses_ReturnCorrectListOfPassedHorses() {
        List<Horse> horses = new ArrayList<>();
        int expectedSize = 30;
        for (int i = 0; i < expectedSize; i++) {
            horses.add(new Horse("Horse" + i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        assertNotNull(hippodrome.getHorses());
        assertEquals(expectedSize, hippodrome.getHorses().size());
        int count = 0;
        for (Horse item: hippodrome.getHorses()) {
            assertEquals("Horse" + count++, item.getName());
        }
    }

    @Test
    void move_CallMoveMethodForAllHorses() {
        List<Horse> horses = new ArrayList<>();
        int expectedSize = 50;
        for (int i = 0; i < expectedSize; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        hippodrome.move();

        for (Horse item: hippodrome.getHorses()) {
            Mockito.verify(item, Mockito.times(1)).move();
        }
    }

    @Test
    void getWinner_ReturnCorrectWinner() {
        Hippodrome hippodrome = new Hippodrome(List.of(
                new Horse("Horse1", 1, 10),
                new Horse("Horse2", 2, 20),
                new Horse("Horse3", 3, 30)
        ));

        assertEquals("Horse3", hippodrome.getWinner().getName());
    }
}
