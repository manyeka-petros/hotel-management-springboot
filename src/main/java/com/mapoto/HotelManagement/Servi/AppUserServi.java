package com.mapoto.HotelManagement.Servi;

;
import com.mapoto.HotelManagement.Entiy.AppUsers;
import com.mapoto.HotelManagement.Entiy.Roles;
import com.mapoto.HotelManagement.Model.AppUserModels;

import java.util.List;

public interface AppUserServi {


    String saveRoles(Roles roles);

    List<Roles> getAllRoles();

    String registerUsers(AppUserModels appUserModels);
}
