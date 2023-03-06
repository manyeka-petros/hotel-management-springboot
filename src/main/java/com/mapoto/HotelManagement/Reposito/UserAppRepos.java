package com.mapoto.HotelManagement.Reposito;

import com.mapoto.HotelManagement.Entiy.AppUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAppRepos extends JpaRepository<AppUsers,Long> {
    Optional<AppUsers> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
