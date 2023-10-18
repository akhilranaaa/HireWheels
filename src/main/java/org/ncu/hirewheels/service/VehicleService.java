package org.ncu.hirewheels.service;

import lombok.RequiredArgsConstructor;
import org.ncu.hirewheels.dto.VehicleDto;
import org.ncu.hirewheels.entities.Booking;
import org.ncu.hirewheels.entities.Vehicle;
import org.ncu.hirewheels.repository.BookingRepository;
import org.ncu.hirewheels.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final BookingRepository bookingRepository;

    private final VehicleRepository vehicleRepository;

    public List<VehicleDto> getAllVehicles()
    {
        return vehicleRepository.findAll().stream().map(this::mapToDTO).toList();
    }

    public List<Vehicle> getAvailableVehicles(Long categoryID, String pickupLocation, ZonedDateTime pickupDate, ZonedDateTime dropoffDate)
    {
        return vehicleRepository.findAvailableVehiclesByCategory(categoryID).stream().map(vehicle -> {

            if (
                    vehicle.getAvailabilityStatus() == 1
                    && vehicle.getLocation().getLocationName().equalsIgnoreCase(pickupLocation)
                    && isAvailableForBooking(vehicle.getVehicleID(), pickupDate, dropoffDate)
            )
                return vehicle;

            else return null;

        }).toList();
    }

    private boolean isAvailableForBooking(Long vehicleID, ZonedDateTime pickupDate, ZonedDateTime dropoffDate) {
        List<Booking> bookings = bookingRepository.findByVehicleID(vehicleID).stream().map(booking -> {

            ZonedDateTime bookingPickupDate = booking.getPickupDate();
            ZonedDateTime bookingDropoffDate = booking.getDropoffDate();

            if (!dropoffDate.isBefore(bookingPickupDate) || !pickupDate.isAfter(bookingDropoffDate))
                return null;

            else return booking;
        }).toList();

        return !bookings.isEmpty();
    }

    private VehicleDto mapToDTO(Vehicle vehicle)
    {
        VehicleDto vehicleDto = new VehicleDto();

        vehicleDto.setVehicleId(vehicle.getVehicleID());
        vehicleDto.setVehicleModel(vehicle.getVehicleModel());
        vehicleDto.setVehicleNumber(vehicle.getVehicleNumber());
        vehicleDto.setVehicleSubcategoryName(vehicle.getVehicleSubcategory().getVehicleSubcategoryName());
        vehicleDto.setColor(vehicle.getColor());
        vehicleDto.setLocationName(vehicle.getLocation().getLocationName());
        vehicleDto.setFuelTypeName(vehicle.getFuelType().getFuelType());
        vehicleDto.setAvailabilityStatus(vehicle.getAvailabilityStatus());
        vehicleDto.setVehicleImageUrl(vehicle.getVehicleImageUrl());

        return vehicleDto;
    }


}
