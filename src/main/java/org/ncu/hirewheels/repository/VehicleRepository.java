package org.ncu.hirewheels.repository;

import org.ncu.hirewheels.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query("SELECT v FROM Vehicle v " +
            "JOIN FETCH v.vehicleSubcategory subcategory " +
            "JOIN FETCH subcategory.vehicleCategory category " +
            "WHERE category.vehicleCategoryID = :categoryID " +
            "AND v.availabilityStatus = 1")
    List<Vehicle> findAvailableVehiclesByCategory(@Param("categoryID") Long categoryID);
}
