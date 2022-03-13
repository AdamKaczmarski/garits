package com.garits.vehicle;

public class VehicleNotFoundException extends RuntimeException {
    VehicleNotFoundException(Integer id) {
        super("Could not find vehicle " + id);
    }
}
