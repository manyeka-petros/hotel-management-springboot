package com.mapoto.HotelManagement.LogIns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:3000")
public class LogInControl {
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/log")
    public String logIns(@RequestBody LogInRequest logInRequest){
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(logInRequest.getUsername(),logInRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (BadCredentialsException e){
            throw new RuntimeException("bad credentials ");
        }

        return "logged in";

    }

}
