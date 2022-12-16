package com.mapoto.HotelManagement.Servi;

import com.mapoto.HotelManagement.Repositor.VisitorRepos;
import com.mapoto.HotelManagement.Entity.VistorsDetail;
import com.mapoto.HotelManagement.Models.Visitorzmodels;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VistorImpleServ implements VisitorServ {

    @Autowired
    private VisitorRepos adminDataReposi;
    @Override
    public String register(Visitorzmodels adminModels) {
        VistorsDetail adminDetails = new VistorsDetail();
        adminDetails.setFirstName(adminModels.getFirstName());
        adminDetails.setLastName(adminModels.getLastName());
        adminDetails.setPhoneNumber(adminModels.getPhoneNumber());
        adminDetails.setEmail(adminModels.getEmail());
        adminDetails.setPassword(adminDetails.getEmail());
        adminDataReposi.save(adminDetails);
        return "Admin has registered";
    }

    @Override
    public List<VistorsDetail> getVisitors() {
        return adminDataReposi.findAll();
    }

}
