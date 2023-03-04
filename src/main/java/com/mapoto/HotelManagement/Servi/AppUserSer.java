package com.mapoto.HotelManagement.Servi;


import com.mapoto.HotelManagement.Entiy.AppUsers;
import com.mapoto.HotelManagement.Reposito.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
public class AppUserSer implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUsers appUser = appUserRepository.findAppUserByEmail(email).orElseThrow(
                ()->    new UsernameNotFoundException("The user with email "+ email+ "not found")
        );


        List<GrantedAuthority> authorities1 = new ArrayList<>();
        authorities1.add(new  SimpleGrantedAuthority(appUser.getRoles().getRoles()));
        return new User(appUser.getEmail(), appUser.getPassword(), authorities1);
    }
    public  int enableAppUser(String email){
        return appUserRepository.enableAppUser(email);
    }
}
