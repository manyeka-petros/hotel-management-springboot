package com.mapoto.HotelManagement.Entiy;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Roles {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String roles;
    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUsers appUsers;
}
