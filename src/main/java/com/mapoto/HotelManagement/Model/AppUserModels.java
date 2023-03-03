package com.mapoto.HotelManagement.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUserModels {
    private String firstname;
    private String lastname;

    private  String email;
    private String password;
    private Set<String> roles;


}
