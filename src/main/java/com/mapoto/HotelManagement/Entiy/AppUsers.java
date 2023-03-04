package com.mapoto.HotelManagement.Entiy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    @OneToOne
    @JoinColumn(name = "roles_id")
    private Roles roles ;
    private boolean enabled;
    private boolean isLocked = false;

    public AppUsers(String firstname, String lastname, String email, String password, Roles roles, boolean enabled) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.enabled = enabled;
    }
    public boolean isEnabled() {
        return enabled;
    }
}
