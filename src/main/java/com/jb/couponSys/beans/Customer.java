package com.jb.couponSys.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jb.couponSys.dto.CustomerPayload;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 40, nullable = false)
    private String firstName;
    @Column(length = 40, nullable = false)
    private String lastName;
    @Column(length = 40, nullable = false)
    private String email;
    @Column(length = 40, nullable = false)
    private String password;
    @ManyToMany
    @Singular("addOne")
    @JsonIgnore
    private List<Coupon> coupons;

    public Customer(CustomerPayload customerPayload) {
        this.firstName = customerPayload.getFirstName();
        this.lastName = customerPayload.getLastName();
        this.email = customerPayload.getEmail();
        this.password = customerPayload.getPassword();
    }
}
