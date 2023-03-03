package com.mapoto.HotelManagement.Reposito;


import com.mapoto.HotelManagement.Entiy.AppUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUsers,Long> {
    //AppUser findAppUserByEmail(String email);


    Optional<AppUsers>  findAppUserByEmail(String email);

    Boolean existsByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE AppUsers a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);
}
