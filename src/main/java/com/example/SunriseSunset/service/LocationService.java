package com.example.SunriseSunset.service;
import com.example.SunriseSunset.model.LocationEntity;
import com.example.SunriseSunset.repository.LocationRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }
    public List<LocationEntity> getAllLocations() {
        return locationRepository.findAll();
    }
    public Optional<LocationEntity> getLocationById(Integer id) {
        return locationRepository.findById(id);
    }
    public LocationEntity createLocation(LocationEntity location) {
        return locationRepository.save(location);
    }
    public LocationEntity updateLocation(Integer id, LocationEntity locationDetails) {
        LocationEntity location = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        location.setName(locationDetails.getName());
        location.setCountry(locationDetails.getCountry());
        return locationRepository.save(location);
    }
    public void deleteLocation(Integer id) {
        locationRepository.deleteById(id);
    }
}