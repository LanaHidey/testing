package com.system.nonfunctional;

import com.system.testing.Calculator;
import com.system.testing.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@DisplayName("Нефункциональное тестирование")
public class PerformanceTest {
    
    public static final double BIG_VALUE_TEST = 0.09; // Эталонное значение выполнения для теста1
    public static final double SEC_FOR_TEST1 = 1; // Эталонное значение выполнения для теста1
    public static final double SEC_FOR_TEST2 = 5; // Эталонное значение выполнения для теста2
    public static final double SEC_FOR_TEST3 = 50; // Эталонное значение выполнения для теста3
    private List<Animal> animals;
    private final Random random = new Random(); // Для барьера

    @BeforeEach
    void init() {
        this.animals = new ArrayList<>();
        for (int i = 0; i < 1000; i ++) {
            animals.add(new Turtle());
        }
        for (int i = 0; i < 1000; i ++) {
            animals.add(new Dog());
        }
        for (int i = 0; i < 1000; i ++) {
            animals.add(new Cat());
        }
        for (int i = 0; i < 1000; i ++) {
            animals.add(new Fish());
        }
        for (int i = 0; i < 1000; i ++) {
            animals.add(new Horse());
        }
    }

    @Test
    @DisplayName("Тест больших значений")
    public void bigValueTest() {
        long start = System.currentTimeMillis();
        Calculator calculator = new Calculator(animals);
        calculator.calc(Integer.MAX_VALUE, Integer.MAX_VALUE, random.nextBoolean());
        long end = System.currentTimeMillis();
        double executionTime = (double) (end - start) / 1000;
        Assertions.assertTrue(executionTime < BIG_VALUE_TEST,"Возможно производительность упала, " +
                "тест выполнялся дольше чем обычно = "  + executionTime + " секунд");
    }

    @Test
    @DisplayName("Тестирование 1 тыс. итераций")
    public void performanceIterTest1() {
        long start = System.currentTimeMillis();
        Calculator calculator = new Calculator(animals);
        for (int i = 0; i < 1000; i ++) {
            calculator.calc(i, i, random.nextBoolean());
        }
        long end = System.currentTimeMillis();
        double executionTime = (double) (end - start) / 1000;
        Assertions.assertTrue(executionTime < SEC_FOR_TEST1,"Возможно производительность упала, " +
                "тест выполнялся дольше чем обычно = "  + executionTime + " секунд");
    }

    @Test
    @DisplayName("Тестирование 10 тыс. итераций")
    public void performanceIterTest2() {
        long start = System.currentTimeMillis();
        Calculator calculator = new Calculator(animals);
        for (int i = 0; i < 10000; i ++) {
            calculator.calc(i, i, random.nextBoolean());
        }
        long end = System.currentTimeMillis();
        double executionTime = (double) (end - start) / 1000;
        Assertions.assertTrue(executionTime < SEC_FOR_TEST2,"Возможно производительность упала, " +
                "тест выполнялся дольше чем обычно = "  + executionTime + " секунд");
    }

    @Test
    @DisplayName("Тестирование 100 тыс. итераций")
    public void performanceIterTest3() {
        long start = System.currentTimeMillis();
        Calculator calculator = new Calculator(animals);
        for (int i = 0; i < 100000; i ++) {
            calculator.calc(i, i, random.nextBoolean());
        }
        long end = System.currentTimeMillis();
        double executionTime = (double) (end - start) / 1000;
        Assertions.assertTrue(executionTime < SEC_FOR_TEST3,"Возможно производительность упала, " +
                "тест выполнялся дольше чем обычно = "  + executionTime + " секунд");
    }
}
