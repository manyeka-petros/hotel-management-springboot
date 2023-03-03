package com.mapoto.HotelManagement.VerifiToken;


import com.mapoto.HotelManagement.Entiy.AppUsers;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@Entity


public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tokenId;
    private String token;
    private LocalDate createdAt;
    private LocalDate confirmedAt;
    private LocalDate expiresAt;
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private AppUsers appUser;

    public VerificationToken(String token, LocalDate createdAt, LocalDate expiresAt, AppUsers appUser) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.appUser = appUser;
    }
}
