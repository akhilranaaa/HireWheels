package org.ncu.hirewheels.repository;

import org.ncu.hirewheels.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b WHERE b.vehicle.vehicleID = :vehicleID")
    List<Booking> findByVehicleID(@Param("vehicleID") Long vehicleID);

}
