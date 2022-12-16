package com.mapoto.HotelManagement.Contro;

import com.mapoto.HotelManagement.Entity.VistorsDetail;
import com.mapoto.HotelManagement.Models.Visitorzmodels;
import com.mapoto.HotelManagement.Servi.VisitorServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VistorCotro {

    @Autowired
    private VisitorServ adminServ;

    @PostMapping("/visitor")
    public  String register(@RequestBody Visitorzmodels adminModels){
        return adminServ.register(adminModels);
    }

    @GetMapping("/visit")

    private List<VistorsDetail> getVistors(){
        return adminServ.getVisitors();
    }



}
