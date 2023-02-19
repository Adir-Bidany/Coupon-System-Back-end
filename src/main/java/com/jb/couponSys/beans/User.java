package com.jb.couponSys.beans;

import com.jb.couponSys.security.ClientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
//@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    //
//    @Id
//    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;
    private String email;
    private String password;
//    @OneToMany(mappedBy = "user",cascade = CascadeType.PERSIST)
//    private List<Task> tasks;

    private ClientType type;
}

