package com.mapoto.HotelManagement.Model;

import com.mapoto.HotelManagement.Entiy.Roles;
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

    private String username;

    private  String email;
    private String password;
    private Roles roles;
    private Set<String> role;


}
