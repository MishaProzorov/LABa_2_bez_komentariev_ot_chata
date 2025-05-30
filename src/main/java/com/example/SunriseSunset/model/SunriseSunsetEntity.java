package com.example.SunriseSunset.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sunrise_and_sunset")
public class SunriseSunsetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Column(name = "sunrise")
    private OffsetDateTime sunrise;

    @Column(name = "sunset")
    private OffsetDateTime sunset;

    @ManyToMany(mappedBy = "sunriseSunsets")
    @JsonBackReference
    private List<LocationEntity> locations = new ArrayList<>();
    public SunriseSunsetEntity() {}
    public SunriseSunsetEntity(LocalDate date, Double latitude, Double longitude) {
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public Double getLatitude() {
        return latitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public Double getLongitude() {
        return longitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    public OffsetDateTime getSunrise() {
        return sunrise;
    }
    public void setSunrise(OffsetDateTime sunrise) {
        this.sunrise = sunrise;
    }
    public OffsetDateTime getSunset() {
        return sunset;
    }
    public void setSunset(OffsetDateTime sunset) {
        this.sunset = sunset;
    }
    public List<LocationEntity> getLocations() {
        return locations;
    }
    public void setLocations(List<LocationEntity> locations) {
        this.locations = locations;
    }
}