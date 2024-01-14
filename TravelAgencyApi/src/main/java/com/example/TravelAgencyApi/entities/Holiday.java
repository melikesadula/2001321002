package com.example.TravelAgencyApi.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import javax.xml.stream.Location;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Table(name = "holidays")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Holiday {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @NotNull
    private int freeSlots;

    @NotNull
    private int duration;

    @NotNull
    private String price;
////

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

}
