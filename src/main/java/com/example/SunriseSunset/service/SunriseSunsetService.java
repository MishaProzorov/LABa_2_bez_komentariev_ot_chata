package com.example.SunriseSunset.service;
import com.example.SunriseSunset.model.LocationEntity;
import com.example.SunriseSunset.model.SunriseSunsetEntity;
import com.example.SunriseSunset.model.SunriseSunsetModel;
import com.example.SunriseSunset.repository.LocationRepository;
import com.example.SunriseSunset.repository.SunriseSunsetRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class SunriseSunsetService {

    private final String SUN_API_URL = "https://api.sunrise-sunset.org/json";
    private final RestTemplate restTemplate;
    private final SunriseSunsetRepository sunriseSunsetRepository;
    private final LocationRepository locationRepository;
    public SunriseSunsetService(RestTemplate restTemplate,
                                SunriseSunsetRepository sunriseSunsetRepository,
                                LocationRepository locationRepository) {
        this.restTemplate = restTemplate;
        this.sunriseSunsetRepository = sunriseSunsetRepository;
        this.locationRepository = locationRepository;
    }
    public SunriseSunsetModel getSunriseSunset(double lat, double lng, String date) {
        String url = String.format("%s?lat=%f&lng=%f&date=%s&formatted=0", SUN_API_URL, lat, lng, date);
        try {
            return restTemplate.getForObject(url, SunriseSunsetModel.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch sunrise/sunset data: " + e.getMessage());
        }
    }
    public SunriseSunsetEntity createSunriseSunset(double latitude, double longitude, String date, List<Integer> locationIds) {
        SunriseSunsetModel model = getSunriseSunset(latitude, longitude, date);
        SunriseSunsetEntity entity = new SunriseSunsetEntity(
                LocalDate.parse(date),
                latitude,
                longitude
        );
        entity.setSunrise(OffsetDateTime.parse(model.getResults().getSunrise()));
        entity.setSunset(OffsetDateTime.parse(model.getResults().getSunset()));

        if (locationIds != null && !locationIds.isEmpty()) {
            List<LocationEntity> locations = locationRepository.findAllById(locationIds);
            entity.setLocations(locations);
        }

        return sunriseSunsetRepository.save(entity);
    }
    public List<SunriseSunsetEntity> getAllSunriseSunsets() {
        return sunriseSunsetRepository.findAll();
    }
    public void deleteSunriseSunset(Integer id) {
        sunriseSunsetRepository.deleteById(id);
    }
}