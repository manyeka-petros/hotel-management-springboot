package com.mapoto.HotelManagement.Reposito;

import com.mapoto.HotelManagement.Entiy.BookRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BookRoomRepository extends JpaRepository<BookRoom,Long> {
}
