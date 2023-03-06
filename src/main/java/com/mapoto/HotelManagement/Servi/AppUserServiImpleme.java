package com.mapoto.HotelManagement.Servi;


import com.mapoto.HotelManagement.Entiy.AppUserRoles;
import com.mapoto.HotelManagement.Entiy.AppUsers;
import com.mapoto.HotelManagement.Entiy.Roles;
import com.mapoto.HotelManagement.Model.AppUserModels;
import com.mapoto.HotelManagement.Reposito.RolesRepos;
import com.mapoto.HotelManagement.Reposito.UserAppRepos;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service

@RequiredArgsConstructor
public class AppUserServiImpleme implements AppUserServi{
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RolesRepos rolesRepos;
    @Autowired
    private UserAppRepos userAppRepos;

    @Override
    public String saveRoles(Roles roles) {
        rolesRepos.save(roles);
        return "role saved";
    }

    @Override
    public List<Roles> getAllRoles() {
        return rolesRepos.findAll();
    }

    @Override
    public String registerUsers(AppUserModels appUserModels) {
        if (userAppRepos.existsByUsername(appUserModels.getUsername())){
            throw new IllegalStateException("User already axist");
        }
        if (userAppRepos.existsByEmail(appUserModels.getEmail())){
            throw new IllegalStateException("The email already exist");
        }
        //CREATING NEW ACCOUNT
        AppUsers appUsers = new AppUsers(
                appUserModels.getUsername(),
                appUserModels.getEmail(),
                passwordEncoder.encode(appUserModels.getPassword())

        );
        Set<String> strRole = appUserModels.getRole();
        Set<Roles> roles = new HashSet<>();
        if (strRole == null){
            Roles rolez  = rolesRepos.findByRolename(AppUserRoles.ROLE_USER).orElseThrow(
                    ()-> new RuntimeException("Role not found")
            );
            roles.add(rolez);
        }
        else {
            strRole.forEach(
                    role-> {
                        switch (role){

                            case "admin":
                                Roles admin = rolesRepos.findByRolename(AppUserRoles.ROLE_ADMIN).orElseThrow(
                                        ()-> new RuntimeException("The addmi not found ")
                                );
                                roles.add(admin);
                                break;
                            default:
                                Roles userRole = rolesRepos.findByRolename(AppUserRoles.ROLE_USER).orElseThrow(
                                        () -> new RuntimeException("user not found")
                                );
                                roles.add(userRole);

                        }
                    }
            );
        }
        appUsers.setRoles(roles);
        userAppRepos.save(appUsers);
        return "user is registered";
    }
}
