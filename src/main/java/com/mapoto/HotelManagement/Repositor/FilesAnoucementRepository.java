package com.mapoto.HotelManagement.Repositor;


import com.mapoto.HotelManagement.Entity.FilesAnoucements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilesAnoucementRepository extends JpaRepository<FilesAnoucements,String > {
}
