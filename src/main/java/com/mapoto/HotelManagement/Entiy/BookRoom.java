package com.mapoto.HotelManagement.Entiy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BookRoom {
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roomId;
    private String username;
    private String email;
    private String address;
    private String phoneNumber;
    private double cost;
    private String bedType;
    private String checkIn;
    private String checkOut;
    private int roomNumber;
    private double totalCost;
}
