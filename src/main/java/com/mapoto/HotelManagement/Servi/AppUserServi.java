package com.mapoto.HotelManagement.Servi;

;
import com.mapoto.HotelManagement.Entiy.AppUsers;
import com.mapoto.HotelManagement.Entiy.Roles;
import com.mapoto.HotelManagement.Model.AppUserModels;

import java.util.List;

public interface AppUserServi {




    String removeUser(Long userId);

    String saveRole(Roles roles);

    String registerUsers(AppUserModels appUserModels);

    List<AppUsers> getAllUser();
}
