package ru.job4j.lsp.parking;

public class Car implements Vehicle {


    final private long id;
    final private int size;

    public Car() {
        this.size = 1;
        this.id = SimpleParking.getParkingId();
    }


    @Override
    public long getId() {
        return id;
    }

    @Override
    public int getSize() {
        return size;
    }
}
