package org.ncu.hirewheels.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID", nullable = false, unique = true)
    private Long userID;

    @NotBlank
    @Size(max=100)
    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Size(max = 100)
    @Column(name = "lastName")
    private String lastName;

    @NotBlank
    @Size(min = 6, max = 50)
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank
    @Email
    @Size(max = 50)
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank
    @Size(min = 10, max = 10)
    @Column(name = "mobileNo", nullable = false, unique = true)
    private String mobileNo;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "roleID", nullable = false)
    private Role roleID;

    @Column(name = "walletMoney", nullable = false, columnDefinition = "NUMERIC(10, 2) default 10000.00")
    private Double walletMoney;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Booking> bookings;

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", roleId=" + roleID.getRoleName() +
                ", walletMoney=" + walletMoney +
                '}';
    }

}
