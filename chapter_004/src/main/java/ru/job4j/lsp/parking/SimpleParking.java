package ru.job4j.lsp.parking;

import java.util.HashMap;
import java.util.Map;

public class SimpleParking implements Parking {

    static private long parkingId = 0;

    private Integer carsPlacesCount;
    private Integer trucksPlacesCount;
    private Map<Long, Vehicle> carsPlaces;
    private Map<Long, Vehicle> truckPlaces;

    public SimpleParking(int carsPlaces, int trucksPlaces) {
        this.carsPlacesCount = carsPlaces;
        this.trucksPlacesCount = trucksPlaces;
        this.carsPlaces = new HashMap<>();
        this.truckPlaces = new HashMap<>();
    }

    @Override
    public long park(Vehicle vehicle) {
        if (findById(vehicle.getId()) != null) {
            throw new IllegalArgumentException();
        }
        long result = -1;
        int vehicleSize = vehicle.getSize();
        if (vehicleSize > 1) {
            if (this.trucksPlacesCount > 0) {
                result = placeVehicle(vehicle, true, this.truckPlaces);
            } else if (this.carsPlacesCount -  vehicleSize > 0) {
                result = placeVehicle(vehicle, false, this.carsPlaces);
            }
        } else {
            if (this.carsPlacesCount -  vehicleSize > 0) {
                result = placeVehicle(vehicle, false, carsPlaces);
            }
        }
        return result;
    }

    private long placeVehicle(Vehicle vehicle, boolean truck, Map<Long, Vehicle> places) {
        long result = -1;
        if (truck) {
            this.trucksPlacesCount--;
        } else {
            this.carsPlacesCount -= vehicle.getSize();
        }
            places.put(vehicle.getId(), vehicle);
            result = vehicle.getId();
        return result;
    }

    @Override
    public Vehicle unpark(long id) {
        Vehicle result = findById(id);
        if (result == null) {
            throw new IllegalArgumentException("Unknown id");
        }
        if (result.getSize() > 1 && this.truckPlaces.remove(id) != null) {
            this.trucksPlacesCount++;
        } else {
            this.carsPlaces.remove(id);
            this.carsPlacesCount += result.getSize();
        }
        return result;
    }

    private Vehicle findById(long id) {
        Vehicle result = carsPlaces.get(id);
        if (result == null) {
            result = truckPlaces.get(id);
        }
        return result;
    }


    @Override
    public int getCarsFreePlaces() {
        return this.carsPlacesCount;
    }

    @Override
    public int getTrucksFreePlaces() {
        return this.trucksPlacesCount;
    }

    public static long getParkingId() {
        return ++parkingId;
    }


}
