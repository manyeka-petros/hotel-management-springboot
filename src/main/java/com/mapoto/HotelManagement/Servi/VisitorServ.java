package com.mapoto.HotelManagement.Servi;

import com.mapoto.HotelManagement.Entity.VistorsDetail;
import com.mapoto.HotelManagement.Models.Visitorzmodels;

import java.util.List;

public interface VisitorServ {
    String register(Visitorzmodels adminModels);


    List<VistorsDetail> getVisitors();
}
