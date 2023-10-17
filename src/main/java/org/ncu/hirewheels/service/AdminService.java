package org.ncu.hirewheels.service;

import lombok.RequiredArgsConstructor;
import org.ncu.hirewheels.dto.VehicleRequest;
import org.ncu.hirewheels.entities.FuelType;
import org.ncu.hirewheels.entities.Location;
import org.ncu.hirewheels.entities.Vehicle;
import org.ncu.hirewheels.entities.VehicleSubcategory;
import org.ncu.hirewheels.repository.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final VehicleRepository vehicleRepository;

    private final LocationRepository locationRepository;

    private final VehicleSubcategoryRepository vehicleSubcategoryRepository;

    private final FuelTypeRepository fuelTypeRepository;

    public Vehicle registerVehicle(VehicleRequest vehicleRequest)
    {
        Vehicle vehicle = mapToDTO(vehicleRequest);

        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateAvailability(Long id, int status)
    {
        Vehicle vehicle = vehicleRepository.findById(id).orElse(null);

        if (vehicle != null)
        {
            vehicle.setAvailabilityStatus(status);
            return vehicleRepository.save(vehicle);
        }

        return null;
    }

    private Vehicle mapToDTO(VehicleRequest vehicleRequest) {
        Vehicle vehicle = new Vehicle();

        VehicleSubcategory vehicleSubcategory = vehicleSubcategoryRepository.findById(vehicleRequest.getVehicleSubCategoryId()).orElse(null);
        Location location = locationRepository.findById(vehicleRequest.getLocationId()).orElse(null);
        FuelType fuelType = fuelTypeRepository.findById(vehicleRequest.getFuelTypeId()).orElse(null);

        vehicle.setVehicleModel(vehicleRequest.getVehicleModel());
        vehicle.setVehicleNumber(vehicleRequest.getVehicleNumber());
        vehicle.setVehicleSubcategory(vehicleSubcategory);
        vehicle.setLocation(location);
        vehicle.setColor(vehicleRequest.getColor());
        vehicle.setFuelType(fuelType);
        vehicle.setAvailabilityStatus(1);
        vehicle.setVehicleImageUrl(vehicleRequest.getVehicleImageUrl());

        return vehicle;
    }
}
