package com.mapoto.HotelManagement.FileMane;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepo extends JpaRepository<FilesInfor,String> {
}
