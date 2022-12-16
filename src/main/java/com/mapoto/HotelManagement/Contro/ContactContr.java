package com.mapoto.HotelManagement.Contro;

import com.mapoto.HotelManagement.Entity.ContactDetails;
import com.mapoto.HotelManagement.Repositor.ContactRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*       ")

@RestController
public class ContactContr {
    @Autowired
    private ContactRepos contactRepos;

    @PostMapping("/contact")

    public String  saveMessage(@RequestBody ContactDetails contactDetails){
        contactRepos.save(contactDetails);
        return "message is saved";
    }
    @GetMapping("/find")
    public List<ContactDetails> getAllMessage(){
        return contactRepos.findAll();
    }
}
