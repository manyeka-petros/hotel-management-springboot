package com.mapoto.HotelManagement.Contro;

import com.mapoto.HotelManagement.Entiy.BookRoom;
import com.mapoto.HotelManagement.Reposito.BookRoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookRoomControl {
    @Autowired
    private BookRoomRepository bookRoomRepository;


    @PostMapping("/book")
    public String bookroom(@RequestBody BookRoom bookRoom){
        bookRoomRepository.save(bookRoom);
        return "room has been bookde";
    }
    @GetMapping("/gt")

    public List<BookRoom> getAllRooms(){
        return bookRoomRepository.findAll();
    }


}
