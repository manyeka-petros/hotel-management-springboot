package com.mapoto.HotelManagement.Repositor;

import com.mapoto.HotelManagement.Entity.VistorsDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface VisitorRepos extends JpaRepository<VistorsDetail,Long> {


}
