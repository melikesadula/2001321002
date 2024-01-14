package com.example.TravelAgencyApi.services;

import com.example.TravelAgencyApi.DTOs.CreateLocationDTO;
import com.example.TravelAgencyApi.DTOs.ResponseLocationDTO;
import com.example.TravelAgencyApi.DTOs.UpdateLocationDTO;
import com.example.TravelAgencyApi.entities.Location;
import com.example.TravelAgencyApi.exeptions.LocationNotFoundException;
import com.example.TravelAgencyApi.repositories.LocationRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseLocationDTO createLocation(CreateLocationDTO createLocationDTO) {
        Location location = modelMapper.map(createLocationDTO, Location.class);
        locationRepository.save(location);
        return modelMapper.map(location, ResponseLocationDTO.class);
    }

    public ResponseLocationDTO getLocationById(Long id) throws LocationNotFoundException {
        Location location = locationRepository.findById(id).orElseThrow(() -> new LocationNotFoundException(id));
        return modelMapper.map(location, ResponseLocationDTO.class);
    }

    public List<ResponseLocationDTO> getAllLocationsByFilters(String name, Date lastUpdated) {
        List<Location> locations = locationRepository.findAll();
        return locations.stream().map(location -> modelMapper.map(location, ResponseLocationDTO.class)).collect(Collectors.toList());
    }

    public ResponseLocationDTO updateLocation(UpdateLocationDTO updateLocation) throws LocationNotFoundException {
        Location location = locationRepository.findById(updateLocation.getId()).orElseThrow(() -> {
            final String errorMessage =
                    String.format("Location not found for id = %s", updateLocation.getId());
            return new LocationNotFoundException(errorMessage);
        });

        location.setCity(updateLocation.getCity());
        location.setCountry(updateLocation.getCountry());
        location.setStreet(updateLocation.getStreet());
        location.setImageUrl(updateLocation.getImageUrl());
        location.setNumber(Integer.parseInt(updateLocation.getNumber()));
        locationRepository.save(location);

        return modelMapper.map(location, ResponseLocationDTO.class);
    }


    @Transactional

    public void deleteLocationById(Long id) throws LocationNotFoundException {
        Location location = locationRepository.findById(id).orElseThrow(() -> {
            final String errorMessage =
                    String.format("Location not found for id = %s", id);
            return new LocationNotFoundException(errorMessage);
        });

        locationRepository.delete(location);
    }
}