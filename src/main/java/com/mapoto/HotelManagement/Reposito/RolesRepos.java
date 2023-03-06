package com.mapoto.HotelManagement.Reposito;

import com.mapoto.HotelManagement.Entiy.AppUserRoles;
import com.mapoto.HotelManagement.Entiy.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepos extends JpaRepository<Roles,Long> {
    Optional<Roles> findByRolename(AppUserRoles rolename);
}
