package org.ncu.hirewheels.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
//import java.util.Date;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "booking")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookingID", nullable = false, unique = true)
    private Long bookingID;

    @NotNull
    @Column(name = "pickup_date", nullable = false)
    private ZonedDateTime pickupDate;

    @NotNull
    @Column(name = "dropoff_date", nullable = false)
    private ZonedDateTime dropoffDate;

    @NotNull
    @Column(name = "booking_date", nullable = false)
    private LocalDate bookingDate;

    @NotNull
    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "locationID", nullable = false)
    @JsonBackReference
    private Location location;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicleID", nullable = false)
    @JsonBackReference
    private Vehicle vehicle;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userID", nullable = false)
    @JsonBackReference
    private Users user;

    // Other methods as needed
    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingID +
                ", pickupDate=" + pickupDate +
                ", dropoffDate=" + dropoffDate +
                ", bookingDate=" + bookingDate +
                ", amount=" + amount +
                ", location=" + location.getLocationName() + // Location
                ", vehicle=" + vehicle.getVehicleModel() + // Vehicle's model
                ", user=" + user.getEmail() + // User's email
                '}';
    }

}
