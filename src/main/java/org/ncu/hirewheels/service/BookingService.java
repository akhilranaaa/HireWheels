package org.ncu.hirewheels.service;

import lombok.RequiredArgsConstructor;
import org.ncu.hirewheels.dto.BookingRequest;
import org.ncu.hirewheels.entities.Booking;
import org.ncu.hirewheels.entities.Location;
import org.ncu.hirewheels.entities.Users;
import org.ncu.hirewheels.entities.Vehicle;
import org.ncu.hirewheels.repository.BookingRepository;
import org.ncu.hirewheels.repository.LocationRepository;
import org.ncu.hirewheels.repository.UserRepository;
import org.ncu.hirewheels.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    private final UserRepository userRepository;

    private final LocationRepository locationRepository;

    private final VehicleRepository vehicleRepository;

    public Booking addBooking (BookingRequest bookingRequest) {
        Booking booking = mapToDTO(bookingRequest);

        Users user = userRepository.findById(booking.getUser().getUserID()).orElse(null);

        if (user != null && BigDecimal.valueOf(user.getWalletMoney()).compareTo(booking.getAmount()) >= 0)
        {
            BigDecimal newBalance = BigDecimal.valueOf(user.getWalletMoney()).subtract(booking.getAmount());

            user.setWalletMoney(newBalance.doubleValue());

            userRepository.save(user);

            return bookingRepository.save(booking);
        }

        else return null;
    }

    private Booking mapToDTO(BookingRequest bookingRequest) {
        Booking booking = new Booking();

        Location location = locationRepository.findById(bookingRequest.getLocationId()).orElse(null);
        Vehicle vehicle = vehicleRepository.findById(bookingRequest.getVehicleId()).orElse(null);
        Users user = userRepository.findById(bookingRequest.getUserId()).orElse(null);

        booking.setUser(user);
        booking.setLocation(location);
        booking.setVehicle(vehicle);
        booking.setAmount(bookingRequest.getAmount());
        booking.setBookingDate(LocalDate.parse(bookingRequest.getBookingDate()));
        booking.setPickupDate(bookingRequest.getPickupDate());
        booking.setDropoffDate(bookingRequest.getDropoffDate());

        return booking;
    }

}
