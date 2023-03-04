package com.mapoto.HotelManagement.Reposito;

import com.mapoto.HotelManagement.Entiy.Complains;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplainsRepos extends JpaRepository<Complains,Long> {
}
