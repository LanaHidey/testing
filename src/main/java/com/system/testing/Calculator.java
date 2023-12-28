package com.system.testing;

import com.system.testing.entity.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Calculator {

    private List<Animal> animals;

    public Calculator() {
        this.animals = new ArrayList<>();
        animals.add(new Dog());
        animals.add(new Cat());
        animals.add(new Horse());
        animals.add(new Turtle());
        animals.add(new Fish());
    }

    public Calculator(List<Animal> animals) {
        this.animals = animals;
    }

    public List<AnimalInfo> calc(int earth, int water, boolean barrier) {
        for (Animal animal : animals) {
            animal.timePath(earth, water, barrier);
        }

        animals = animals.stream().sorted((Comparator.comparingDouble(Animal::getTimePath))).collect(Collectors.toList());


        List<AnimalInfo> result = new ArrayList<>();
        for (Animal animal : animals) {
            if (animal.getTimePath() < 0) {
                result.add(new AnimalInfo(animal.getAnimalType(), "не может завершить дистанцию"));
            } else {
                result.add(new AnimalInfo(animal.getAnimalType(), animal.getTimePath() + " минут"));
            }
        }
        return result;
    }

}
