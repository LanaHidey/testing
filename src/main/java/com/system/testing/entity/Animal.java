package com.system.testing.entity;

import com.system.testing.Utils;

public class Animal {

    private final double runningSpeed;
    private final double swimmingSpeed;
    private final boolean jump;
    private final String coatColor;
    private final boolean tail;
    private final String animalType;
    private double timePath;

    public Animal(double runningSpeed, double swimmingSpeed, boolean jump, String coatColor, boolean tail, String animalType) {
        this.runningSpeed = runningSpeed;
        this.swimmingSpeed = swimmingSpeed;
        this.jump = jump;
        this.coatColor = coatColor;
        this.tail = tail;
        this.animalType = animalType;
    }

    public Animal timePath(int earth, int water, boolean barrier) {
        if ((!jump && barrier) || (earth != 0 && runningSpeed == 0) || (water != 0 && swimmingSpeed == 0)) {
            this.timePath = -1;
            return this;
        }
        double earthTime = earth != 0 ? earth / Utils.mpsToKmh(runningSpeed) : 0;
        double waterTime = water !=0 ? water / Utils.mpsToKmh(swimmingSpeed) : 0;
        this.timePath = Math.round((earthTime + waterTime) * 60);
        return this;
    }

    public double getRunningSpeed() {
        return runningSpeed;
    }

    public double getSwimmingSpeed() {
        return swimmingSpeed;
    }

    public boolean isJump() {
        return jump;
    }

    public String getCoatColor() {
        return coatColor;
    }

    public boolean isTail() {
        return tail;
    }

    public String getAnimalType() {
        return animalType;
    }

    public double getTimePath() {
        return timePath;
    }
}
