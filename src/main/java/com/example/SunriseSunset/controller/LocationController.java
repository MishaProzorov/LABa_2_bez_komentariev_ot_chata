package com.example.SunriseSunset.controller;

import com.example.SunriseSunset.model.LocationEntity;
import com.example.SunriseSunset.service.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public List<LocationEntity> getAllLocations() {
        return locationService.getAllLocations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationEntity> getLocationById(@PathVariable Integer id) {
        return locationService.getLocationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public LocationEntity createLocation(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Required parameter 'name' is missing or empty");
        }
        String country = request.get("country");
        LocationEntity location = new LocationEntity(name, country);
        return locationService.createLocation(location);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocationEntity> updateLocation(@PathVariable Integer id, @RequestBody Map<String, String> request) {
        String name = request.get("name");
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Required parameter 'name' is missing or empty");
        }
        String country = request.get("country");
        LocationEntity locationDetails = new LocationEntity(name, country);
        try {
            LocationEntity updatedLocation = locationService.updateLocation(id, locationDetails);
            return ResponseEntity.ok(updatedLocation);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Integer id) {
        locationService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }
}