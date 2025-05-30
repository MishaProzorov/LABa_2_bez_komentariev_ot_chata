package com.example.SunriseSunset.model;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "locations")
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "country")
    private String country;

    @ManyToMany
    @JoinTable(
            name = "sunrise_sunset_locations",
            joinColumns = @JoinColumn(name = "location_id"),
            inverseJoinColumns = @JoinColumn(name = "sunrise_sunset_id")
    )
    @com.fasterxml.jackson.annotation.JsonManagedReference
    private List<SunriseSunsetEntity> sunriseSunsets = new ArrayList<>();

    public LocationEntity() {}

    public LocationEntity(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<SunriseSunsetEntity> getSunriseSunsets() {
        return sunriseSunsets;
    }

    public void setSunriseSunsets(List<SunriseSunsetEntity> sunriseSunsets) {
        this.sunriseSunsets = sunriseSunsets;
    }
}