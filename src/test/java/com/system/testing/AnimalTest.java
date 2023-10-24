package com.system.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnimalTest {
    private List<Animal> animals;

    @BeforeEach
    void init() {
        this.animals = new ArrayList<>();
        animals.add(new Turtle());
        animals.add(new Dog());
        animals.add(new Cat());
        animals.add(new Fish());
        animals.add(new Horse());
    }

    @Test
    @DisplayName("Тест на барьеры")
    void barrierTest() {
        assertEquals(-1, animals.get(0).timePath(5, 2, true).getTimePath(),
                "черепаха не умеет преодолевать барьеры, -1 = незавершенная дистанция");
    }

    @Test
    @DisplayName("Тест при отсутствии барьера")
    void noBarrierTest() {
        assertTrue(animals.get(0).timePath(5, 2, false).getTimePath() > 0,
                "черепаха должна пройти дистанцию, без барьера");
    }

    @Test
    @DisplayName("Тест перевода м/с в км/ч")
    void mpsToKmhTest() {
        assertEquals(72, Utils.mpsToKmh(20), "Неправильный перевод м/с в км/ч");
    }

    @Test
    @DisplayName("Тест рыбы")
    void noRunningSpeedTest() {
        assertTrue(animals.get(3).timePath(5, 2, false).getTimePath() < 0,
                "рыба не умеет бегать, поэтому не может завершить дистанцию");
    }

    @ParameterizedTest(name = "Тест всех животных")
    @CsvSource({
            "0, 5,    5,   -1",
            "1, 5,    5,   95",
            "2, 5,  0, 27",
            "3, 0,  5, 17",
            "4, 10,  10, 161"
    })
    void allAnimalsTest(int animal, int earth, int water, int expectedResult) {
        assertEquals(expectedResult, animals.get(animal).timePath(earth, water, true).getTimePath(), "животное " + animals.get(animal).getAnimalType() + " пробежало неправильно");
    }
}
