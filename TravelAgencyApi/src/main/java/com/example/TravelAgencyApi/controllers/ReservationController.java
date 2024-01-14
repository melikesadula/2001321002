package com.example.TravelAgencyApi.controllers;


import com.example.TravelAgencyApi.DTOs.CreateReservationDTO;
import com.example.TravelAgencyApi.DTOs.ResponseReservationDTO;
import com.example.TravelAgencyApi.DTOs.UpdateReservationDTO;
import com.example.TravelAgencyApi.exeptions.HolidayNotFoundException;
import com.example.TravelAgencyApi.exeptions.ReservationNotFoundException;
import com.example.TravelAgencyApi.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController @RequestMapping("/reservations")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseReservationDTO> createReservation(@RequestBody CreateReservationDTO createReservationDTO)
    { ResponseReservationDTO response = reservationService.createReservation(createReservationDTO);
        return new ResponseEntity<>(response, HttpStatus.OK); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteReservationById(@PathVariable Long id) throws ReservationNotFoundException { reservationService.deleteReservationById(id);
        return new ResponseEntity<>(true, HttpStatus.OK); }
    @GetMapping
    public ResponseEntity<List<ResponseReservationDTO>> getAllReservations() {
        List<ResponseReservationDTO> reservations = reservationService.getAllReservation();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseReservationDTO> getReservationById(@PathVariable Long id) throws ReservationNotFoundException { ResponseReservationDTO reservation = reservationService.getReservationById(id);
        return new ResponseEntity<>(reservation, HttpStatus.OK); }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseReservationDTO> updateReservation(@RequestBody UpdateReservationDTO updateReservationDTO) throws ReservationNotFoundException,
            HolidayNotFoundException { ResponseReservationDTO reservation = reservationService.updateReservation(updateReservationDTO);
        return new ResponseEntity<>(reservation, HttpStatus.OK); }

}

