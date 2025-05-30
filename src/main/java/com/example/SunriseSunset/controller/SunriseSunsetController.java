package com.example.SunriseSunset.controller;
import com.example.SunriseSunset.model.SunriseSunsetEntity;
import com.example.SunriseSunset.model.SunriseSunsetModel;
import com.example.SunriseSunset.service.SunriseSunsetService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
public class SunriseSunsetController {

    private final SunriseSunsetService sunService;

    public SunriseSunsetController(SunriseSunsetService sunService) {
        this.sunService = sunService;
    }
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("name", "Главная страница");
        return "Home";
    }
    @GetMapping("/sun/times")
    public ResponseEntity<SunriseSunsetModel> getSunriseSunset(
            @RequestBody Map<String, Object> request) {
        if (!request.containsKey("lat") || !request.containsKey("lng") || !request.containsKey("date")) {
            throw new IllegalArgumentException("Required fields (lat, lng, date) are missing");
        }

        double lat = Double.parseDouble(request.get("lat").toString());
        double lng = Double.parseDouble(request.get("lng").toString());
        String date = request.get("date").toString();

        SunriseSunsetModel sunResponse = sunService.getSunriseSunset(lat, lng, date);
        return ResponseEntity.ok(sunResponse);
    }

    @PostMapping("/sun/times")
    public ResponseEntity<SunriseSunsetEntity> createSunriseSunset(
            @RequestBody Map<String, Object> request) {
        if (!request.containsKey("latitude") || !request.containsKey("longitude") || !request.containsKey("date")) {
            throw new IllegalArgumentException("Required fields (latitude, longitude, date) are missing");
        }
        double latitude = Double.parseDouble(request.get("latitude").toString());
        double longitude = Double.parseDouble(request.get("longitude").toString());
        String date = request.get("date").toString();
        List<Integer> locationIds = request.containsKey("locationIds")
                ? ((List<?>) request.get("locationIds")).stream()
                .map(obj -> Integer.parseInt(obj.toString()))
                .toList()
                : null;
        SunriseSunsetEntity savedEntity = sunService.createSunriseSunset(
                latitude, longitude, date, locationIds
        );
        return ResponseEntity.ok(savedEntity);
    }
    @GetMapping("/sun/times/all")
    public List<SunriseSunsetEntity> getAllSunriseSunsets() {
        return sunService.getAllSunriseSunsets();
    }
}