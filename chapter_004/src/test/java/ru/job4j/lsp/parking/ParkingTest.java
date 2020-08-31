package ru.job4j.lsp.parking;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ParkingTest  {

    @Test
    public void overParkingTest() {
        Parking parking = new SimpleParking(4, 1);
        Vehicle car = new Car();
        Vehicle truck1 = new Truck(2);
        Vehicle truck2 = new Truck(2);

        parking.park(car);
        assertThat(parking.getCarsFreePlaces(), is(3));
        assertThat(parking.getTrucksFreePlaces(), is(1));

        parking.park(truck1);
        assertThat(parking.getCarsFreePlaces(), is(3));
        assertThat(parking.getTrucksFreePlaces(), is(0));

        parking.park(truck2);
        assertThat(parking.getCarsFreePlaces(), is(1));
        assertThat(parking.getTrucksFreePlaces(), is(0));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testForUniqueVechile() {
        Parking parking = new SimpleParking(4, 1);
        Vehicle car = new Car();
        parking.park(car);
        parking.park(car);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testDouleUnparking() {
        Parking parking = new SimpleParking(4, 1);
        Vehicle car = new Car();
        parking.park(car);
        parking.unpark(car.getId());
        parking.unpark(car.getId());
    }

    @Test
    public void testGetFreePlaces() {
        Parking parking = new SimpleParking(4, 1);
        Vehicle car = new Car();
        Vehicle truck1 = new Truck(2);

        parking.park(car);
        assertThat(parking.getCarsFreePlaces(), is(3));
        assertThat(parking.getTrucksFreePlaces(), is(1));

        parking.park(truck1);
        assertThat(parking.getCarsFreePlaces(), is(3));
        assertThat(parking.getTrucksFreePlaces(), is(0));

        var leavingCTruck = parking.unpark(truck1.getId());

        assertThat(leavingCTruck, is(truck1));
        assertThat(parking.getTrucksFreePlaces(), is(1));

        var leavingCar = parking.unpark(car.getId());

        assertThat(leavingCar, is(car));
        assertThat(parking.getCarsFreePlaces(), is(4));
    }
}