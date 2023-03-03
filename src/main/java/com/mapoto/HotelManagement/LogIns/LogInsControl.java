package com.mapoto.HotelManagement.LogIns;

import com.mapoto.HotelManagement.Servi.AppUserSer;
import com.mapoto.HotelManagement.Servi.AppUserServiImpleme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class LogInsControl {
    @Autowired
    private AppUserServiImpleme appUserServiImpleme;
    @Autowired
    private  AuthenticationManager authenticationManager;

    @Autowired
    private AppUserSer appUserSer;
    @PostMapping("/sail")

    public String logIns(@RequestBody LogInRequest logInRequest){
        Authentication authentication;
        try {
           authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    logInRequest.getEmail(),logInRequest.getPassword()
            ));
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }catch (BadCredentialsException e){
            throw new BadCredentialsException("The email " + logInRequest.getEmail() + " or " + "   password " +
                    logInRequest.getPassword() +" is not correct");

        }
        //GENERATING TOKEN OF BEING AUTHENTICATED
        final UserDetails userDetails = appUserSer.loadUserByUsername(logInRequest.getEmail());

        return "your token is logged in "  ;
    }
    @GetMapping("/confirm")
    public String confirmToken(@RequestParam("token") String token){
        return appUserServiImpleme.confirmToken(token);

    }
}
