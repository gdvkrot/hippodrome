import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HorseTest {
    @Test
    void constructor_NullNameParamPassed_ThrowsIllegalArgumentException() {
        String name = null;
        double speed = 100;
        double distance = 200;

        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
    }

    @Test
    void constructor_NullNameParamPassed_ThrowsIllegalArgumentException_ReturnMessage() {
        String expectedMessage = "Name cannot be null.";
        String name = null;
        double speed = 100;
        double distance = 200;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));

        assertEquals(expectedMessage, exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\n", "\t"})
    void constructor_EmptyNameParamPassed_ThrowsIllegalArgumentException(String parameter) {
        double speed = 100;
        double distance = 200;

        assertThrows(IllegalArgumentException.class, () -> new Horse(parameter, speed, distance));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\n", "\t"})
    void constructor_EmptyNameParamPassed_ThrowsIllegalArgumentException_ReturnMessage(String parameter) {
        String expectedMessage = "Name cannot be blank.";
        double speed = 100;
        double distance = 200;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(parameter, speed, distance));

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void constructor_NegativeSpeedParamPassed_ThrowsIllegalArgumentException() {
        String name = "null";
        double speed = -100;
        double distance = 200;

        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
    }

    @Test
    void constructor_NegativeSpeedParamPassed_ThrowsIllegalArgumentException_ReturnMessage() {
        String expectedMessage = "Speed cannot be negative.";
        String name = "null";
        double speed = -100;
        double distance = 200;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void constructor_NegativeDistanceParamPassed_ThrowsIllegalArgumentException() {
        String name = "null";
        double speed = 100;
        double distance = -200;

        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
    }

    @Test
    void constructor_NegativeDistanceParamPassed_ThrowsIllegalArgumentException_ReturnMessage() {
        String expectedMessage = "Distance cannot be negative.";
        String name = "null";
        double speed = 100;
        double distance = -200;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getName_ReturnCorrectName() {
        String name = "null";
        double speed = 100;
        double distance = 200;
        Horse horse = new Horse(name, speed, distance);

        String actualName = horse.getName();

        assertEquals(name, actualName);
    }

    @Test
    void getSpeed_ReturnCorrectSpeed() {
        String name = "null";
        double speed = 100;
        double distance = 200;
        Horse horse = new Horse(name, speed, distance);

        double actualSpeed = horse.getSpeed();

        assertEquals(speed, actualSpeed);
    }

    @Test
    void getDistance_ReturnCorrectDistance() {
        String name = "null";
        double speed = 100;
        double distance = 200;
        Horse horse = new Horse(name, speed, distance);

        double actualDistance = horse.getDistance();

        assertEquals(distance, actualDistance);
    }

    @Test
    void getDistance_ReturnZeroDistanceIfCalledConstructorWithTwoParameters() {
        String name = "null";
        double speed = 100;
        double expectedDistance = 0;
        Horse horse = new Horse(name, speed);

        double actualDistance = horse.getDistance();

        assertEquals(expectedDistance, actualDistance);
    }

    @Test
    void move_CallsGetRandomDoubleMethod() {
        String name = "null";
        double speed = 100;
        double distance = 200;
        Horse horse = new Horse(name, speed, distance);

        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            horse.move();

            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 10, 150, -1525})
    void move_Calculates(double parameter) {
        double min = 0.2;
        double max = 0.9;
        String name = "null";
        double speed = 100;
        double distance = 200;
        Horse horse = new Horse(name, speed, distance);

        double expectedDistance = distance + speed * parameter;

        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(min, max)).thenReturn(parameter);
            horse.move();
        }

        assertEquals(expectedDistance, horse.getDistance());
    }
}
