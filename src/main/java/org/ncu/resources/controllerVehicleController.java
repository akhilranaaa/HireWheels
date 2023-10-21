package org.ncu.hirewheels.controller;

import lombok.RequiredArgsConstructor;
import org.ncu.hirewheels.dto.VehicleDto;
import org.ncu.hirewheels.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping("/all")
    public ResponseEntity<List<VehicleDto>> getAllVehicles() {

        List<VehicleDto> vehicleDtos = vehicleService.getAllVehicles();

        return vehicleDtos != null
                ? new ResponseEntity<>(vehicleDtos, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
