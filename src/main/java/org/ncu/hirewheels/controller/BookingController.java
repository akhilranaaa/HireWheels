package org.ncu.hirewheels.controller;

import lombok.RequiredArgsConstructor;
import org.ncu.hirewheels.dto.BookingRequest;
import org.ncu.hirewheels.entities.Booking;
import org.ncu.hirewheels.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking> addBooking(@RequestBody BookingRequest bookingRequest)
    {
        Booking addedBooking = bookingService.addBooking(bookingRequest);

        return addedBooking != null
                ? new ResponseEntity<>(addedBooking, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
