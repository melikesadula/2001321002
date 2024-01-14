package com.example.TravelAgencyApi.services;


import com.example.TravelAgencyApi.DTOs.CreateReservationDTO;
import com.example.TravelAgencyApi.DTOs.ResponseReservationDTO;
import com.example.TravelAgencyApi.DTOs.UpdateReservationDTO;
import com.example.TravelAgencyApi.entities.Holiday;
import com.example.TravelAgencyApi.entities.Location;
import com.example.TravelAgencyApi.entities.Reservation;
import com.example.TravelAgencyApi.exeptions.HolidayNotFoundException;
import com.example.TravelAgencyApi.exeptions.ReservationNotFoundException;
import com.example.TravelAgencyApi.repositories.HolidayRepository;
import com.example.TravelAgencyApi.repositories.LocationRepository;
import com.example.TravelAgencyApi.repositories.ReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseReservationDTO createReservation(CreateReservationDTO createReservationDTO) {
        List<Location> locations = locationRepository.findAllById(createReservationDTO.getLocationIds());
        Reservation reservation = new Reservation();
        reservation.setContactName(createReservationDTO.getContactName());
        reservation.setCustomerName(createReservationDTO.getCustomerName());
        reservation.setPhoneNumber(createReservationDTO.getPhoneNumber());
//        reservation.setHoliday(createReservationDTO.getHoliday());
        reservationRepository.save(reservation);
        return modelMapper.map(reservation, ResponseReservationDTO.class);
    }


    public ResponseReservationDTO getReservationById(Long id) throws ReservationNotFoundException {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException(id));
        return modelMapper.map(reservation, ResponseReservationDTO.class);
    }

    public List<ResponseReservationDTO> getAllReservation() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream().map(reservation -> modelMapper.map(reservation, ResponseReservationDTO.class)).collect(Collectors.toList());
    }

    public void deleteReservationById(Long id) throws ReservationNotFoundException {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException(id));
        reservationRepository.delete(reservation);
    }

    @Autowired
    private HolidayRepository holidayRepository;

    public ResponseReservationDTO updateReservation(UpdateReservationDTO updateReservation) throws ReservationNotFoundException, HolidayNotFoundException {
        Reservation reservation = reservationRepository.findById(updateReservation.getId()).orElseThrow(() -> {
            final String errorMessage =
                    String.format("Reservation not found for id = %s", updateReservation.getId());
            return new ReservationNotFoundException(errorMessage);
        });

        if (updateReservation.getHoliday() != null) {
            Holiday holiday = holidayRepository.findById(updateReservation.getHoliday())
                    .orElseThrow(() -> {
                        return new HolidayNotFoundException("Holiday not found for id = " + updateReservation.getHoliday());
                    });
            reservation.setHoliday(holiday);
        }

        reservation.setContactName(updateReservation.getContactName());
        reservation.setPhoneNumber(updateReservation.getPhoneNumber());
        reservationRepository.save(reservation);

        return modelMapper.map(reservation, ResponseReservationDTO.class);
    }
}
