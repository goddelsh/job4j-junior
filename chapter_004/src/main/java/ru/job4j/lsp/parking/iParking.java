package ru.job4j.lsp.parking;


public interface iParking {
    long park(iVehicle vehicle);
    iVehicle unpark(long id);
    int getFreePlaces();
}
