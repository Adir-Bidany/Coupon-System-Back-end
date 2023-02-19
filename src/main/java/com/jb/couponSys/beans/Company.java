package com.jb.couponSys.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jb.couponSys.dto.CompanyPayload;
import com.jb.couponSys.dto.UpdateCompanyPayload;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "companies")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 40, nullable = false)
    private String name;
    @Column(length = 40, nullable = false)
    private String email;
    @Column(length = 40, nullable = false)
    private String password;
    @OneToMany(mappedBy = "company", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @ToString.Exclude
    @Singular
    @JsonIgnore
    private List<Coupon> coupons;

    public Company(CompanyPayload companyPayload) {
        this.name = companyPayload.getName();
        this.email = companyPayload.getEmail();
        this.password = companyPayload.getPassword();
    }

    public Company(UpdateCompanyPayload updateCompanyPayload) {
        this.email = updateCompanyPayload.getEmail();
        this.password = updateCompanyPayload.getPassword();
    }

}
