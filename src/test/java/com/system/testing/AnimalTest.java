package com.system.testing;

import com.system.testing.entity.*;
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


        // Интеграционный, тест для метода calc класса Calculator, чтобы убедиться, что он возвращает правильный список AnimalInfo
        @Test
        void calcTest() {
            Calculator calculator = new Calculator();
            List<AnimalInfo> result = calculator.calc(10, 20, false);
            // проверяем ожидаемый результат с использованием assertEquals
            assertEquals(5, result.size(), "Должен вернутьсясписок из 5 объектов");

            // Пример проверки содержания конкретных AnimalInfo
            assertEquals("рыба", result.get(0).getName(), "Первое животное должно быть рыбой");
            assertEquals("не может завершить дистанцию", result.get(0).getInfo(), "Информация о рыбе должна быть - не может завершить дистанцию");
        }

    @BeforeEach
    void init() {
        this.animals = new ArrayList<>();
        animals.add(new Turtle());
        animals.add(new Dog());
        animals.add(new Cat());
        animals.add(new Fish());
        animals.add(new Horse());
    }

    // Функциональные так как они проверяют ожидаемое поведение и реакцию животных на конкретные условия
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


    // Модульный, поскольку он проверяет функциональность конкретного метода из класса Utils
    @Test
    @DisplayName("Тест перевода м/с в км/ч")
    void mpsToKmhTest() {
        assertEquals(72, Utils.mpsToKmh(20), "Неправильный перевод м/с в км/ч");
    }


    // Функциональный, проверяется ожидаемое поведение системы
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
    // Функциональный, проверяется ожидаемое поведение для всех животных при различных условиях
    void allAnimalsTest(int animal, int earth, int water, int expectedResult) {
        assertEquals(expectedResult, animals.get(animal).timePath(earth, water, true).getTimePath(), "животное " + animals.get(animal).getAnimalType() + " пробежало неправильно");
    }
}
