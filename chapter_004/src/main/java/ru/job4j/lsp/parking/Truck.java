package ru.job4j.lsp.parking;

public class Truck implements Vehicle {


    final private long id;
    final private int size;

    public Truck(int size) {
        if (size < 2) {
            throw new IllegalArgumentException();
        }
        this.size = size;
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
