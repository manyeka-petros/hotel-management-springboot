package com.mapoto.HotelManagement.LogIns;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class LogInRequest {
    private String username;
    private String password;
}
