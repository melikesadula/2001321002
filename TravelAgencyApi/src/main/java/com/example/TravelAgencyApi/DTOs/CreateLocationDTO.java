package com.example.TravelAgencyApi.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateLocationDTO {

    private String name;
    private String description;
    private String address;
    private Double latitude;
    private Double longitude;

}