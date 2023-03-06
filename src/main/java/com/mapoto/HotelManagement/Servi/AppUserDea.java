package com.mapoto.HotelManagement.Servi;

import com.mapoto.HotelManagement.Entiy.AppUserDetails;
import com.mapoto.HotelManagement.Entiy.AppUsers;
import com.mapoto.HotelManagement.Reposito.UserAppRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDea implements UserDetailsService {
    @Autowired
    private UserAppRepos userAppRepos;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUsers appUsers = userAppRepos.findByUsername(username).orElseThrow(
                ()-> new UsernameNotFoundException("there is no such user")
        );
        return AppUserDetails.creat(appUsers);
    }
}
