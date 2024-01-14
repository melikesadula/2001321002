package com.example.TravelAgencyApi.controllers;

import com.example.TravelAgencyApi.DTOs.CreateLocationDTO;
//import com.example.TravelAgencyApi.DTOs.LocationDTO;
import com.example.TravelAgencyApi.DTOs.ResponseLocationDTO;
import com.example.TravelAgencyApi.DTOs.UpdateLocationDTO;
import com.example.TravelAgencyApi.exeptions.LocationNotFoundException;
import com.example.TravelAgencyApi.services.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/locations")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class LocationController {

    private final LocationService locationService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseLocationDTO> createLocation(@RequestBody CreateLocationDTO createLocationDTO) {
        ResponseLocationDTO response = locationService.createLocation(createLocationDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) throws LocationNotFoundException {
        locationService.deleteLocationById(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ResponseLocationDTO>> getAllLocations(
            @RequestParam(required = false) String name,
            @RequestParam(required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date lastUpdated) {
        List<ResponseLocationDTO> locations = locationService.getAllLocationsByFilters(name, lastUpdated);
        return new ResponseEntity<>(locations, HttpStatus.OK); }



    @GetMapping("/{id}") public ResponseEntity<ResponseLocationDTO> getLocationById(@PathVariable Long id) throws LocationNotFoundException {
        ResponseLocationDTO location = locationService.getLocationById(id); return new ResponseEntity<>(location, HttpStatus.OK); }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseLocationDTO> updateLocation(@RequestBody UpdateLocationDTO updateLocationDTO) throws LocationNotFoundException {ResponseLocationDTO location = locationService.updateLocation(updateLocationDTO); return new ResponseEntity<>(location, HttpStatus.OK); }

}