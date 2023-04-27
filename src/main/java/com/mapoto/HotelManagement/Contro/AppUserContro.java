package com.mapoto.HotelManagement.Contro;



import com.mapoto.HotelManagement.Entiy.AppUsers;
import com.mapoto.HotelManagement.Entiy.Roles;
import com.mapoto.HotelManagement.LogIns.LogInRequest;
import com.mapoto.HotelManagement.Model.AppUserModels;
import com.mapoto.HotelManagement.Servi.AppUserServi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class AppUserContro {

    @Autowired
    private AppUserServi appUserServi;
    @PostMapping("/role")
    public String saveRoles(@RequestBody Roles roles){
       return   appUserServi.saveRoles(roles);
    }

    @GetMapping("/rol")
    @PreAuthorize("hasRole('USER')")
    public List<Roles> getAllRoles(){
        return appUserServi.getAllRoles();
    }
    @PostMapping("/save")

    public String registerUsers(@RequestBody AppUserModels appUserModels){
        return appUserServi.registerUsers(appUserModels);
    }
    @GetMapping("/look")
    @PreAuthorize("hasRole('USER')")
    public String loo(){
        return "you are looking";
    }



}
