package ru.job4j.lsp.parking;

public class SimpleParking implements Parking {
    public SimpleParking(int carsPllaces, int trucksPlaces) {
    }

    @Override
    public long park(Vehicle vehicle) {
        return 0;
    }

    @Override
    public Vehicle unpark(long id) {
        return null;
    }

    @Override
    public int getCarsFreePlaces() {
        return 0;
    }

    @Override
    public int getTrucksFreePlaces() {
        return 0;
    }


}
