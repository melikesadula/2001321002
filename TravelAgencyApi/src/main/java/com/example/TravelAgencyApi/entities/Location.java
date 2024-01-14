package com.example.TravelAgencyApi.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "locations")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String locationName;

    @NotNull
    private int number;

    @NotNull
    private String country;

    @NotNull
    private String city;

    @NotNull
    private String address;

    @NotNull
    private String imageUrl;

    @NotNull
    private String street;

    @OneToMany(mappedBy = "location", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Holiday> holidays;


}
