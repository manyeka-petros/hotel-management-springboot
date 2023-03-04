package com.mapoto.HotelManagement.Contro;

import com.mapoto.HotelManagement.Entiy.Complains;
import com.mapoto.HotelManagement.Reposito.ComplainsRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class ComplainsContro {
    @Autowired
    private ComplainsRepos complainsRepos;

    @PostMapping("/sa")
    private String sendComplian(@RequestBody Complains complains){
        complainsRepos.save(complains);
        return "complain sent";
    }
    @GetMapping("/ge")

    public List<Complains> getAllComplains(){
        return complainsRepos.findAll();
    }
}
