package io.github.rvdxk.carrentalspringproject.service;

import io.github.rvdxk.carrentalspringproject.entity.Location;

import java.util.List;

public interface LocationService {

    List<Location> getAllLocation();
    Location getLocationById(Long id);
    void addLocation(Location location);
    void updateLocation(Location location, Long id);
    void deleteLocation(Long id);
}
