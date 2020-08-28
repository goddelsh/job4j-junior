package ru.job4j.lsp.parking;


public interface Parking {
    long park(Vehicle vehicle);
    Vehicle unpark(long id);
    int getCarsFreePlaces();
    int getTrucksFreePlaces();
}
