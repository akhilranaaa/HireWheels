package org.ncu.hirewheels.controller;
import lombok.RequiredArgsConstructor;
import org.ncu.hirewheels.service.AdminService;
import org.springframework.web.bind.annotation.*;
import org.ncu.hirewheels.entities.Vehicle;
import org.ncu.hirewheels.dto.UpdateAvailabilityRequest;
import org.ncu.hirewheels.dto.VehicleRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	private final AdminService adminService;

    @GetMapping("/base")
    public String baseReq() {
        return "Working on base";
    }

    @PostMapping(value = "/vehicles/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> AddVehicle(@RequestBody VehicleRequest vehicleRequest) {

        logger.info("Received request body: {}",  vehicleRequest);

        Vehicle vehicle = adminService.registerVehicle(vehicleRequest);

        return new ResponseEntity<>("Vehicle added successfully", vehicle != null ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }

    // Update the availability status of the specified vehicle by ID
    @PatchMapping("/vehicles/{id}")
    public ResponseEntity<Vehicle> updateVehicleAvailability(
            @PathVariable Long id,
            @RequestBody UpdateAvailabilityRequest request) {

        if (request != null && request.getAvailabilityStatus() != null) {

            Vehicle updatedVehicle = adminService.updateAvailability(id, request.getAvailabilityStatus());

            if (updatedVehicle != null) {

                return new ResponseEntity<>(updatedVehicle, HttpStatus.ACCEPTED);
            } else {

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

