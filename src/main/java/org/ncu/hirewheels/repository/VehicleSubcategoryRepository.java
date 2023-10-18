package org.ncu.hirewheels.repository;

import org.ncu.hirewheels.entities.Vehicle;
import org.ncu.hirewheels.entities.VehicleSubcategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleSubcategoryRepository extends JpaRepository<VehicleSubcategory, Long> {
}
