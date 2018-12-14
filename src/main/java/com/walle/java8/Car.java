package com.walle.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author: bbpatience
 * @date: 2018/12/14
 * @description: Car
 **/
public class Car {

    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }

    public static void collide(final Car car) {
        System.out.println("Collided " + car.toString());
    }

    public void follow(final Car another) {
        System.out.println("Following the " + another.toString());
    }

    public void repair() {
        System.out.println("Repaired " + this.toString());
    }

    public static void main(String[] args) {
        Car car = Car.create(Car::new);
        car.repair();
        final List< Car > cars = Collections.singletonList(car);

        cars.forEach(Car::repair);
    }
}
