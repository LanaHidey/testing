package com.system.testing;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        int earth = 10;
        int water = 20;
        boolean barrier = false;

        Calculator calculator = new Calculator();
        for (AnimalInfo animalInfo : calculator.calc(earth, water, barrier)) {
            System.out.println(animalInfo.getName() + " " + animalInfo.getInfo());
        }
    }
}
