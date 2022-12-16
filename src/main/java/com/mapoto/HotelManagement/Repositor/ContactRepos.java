package com.mapoto.HotelManagement.Repositor;

import com.mapoto.HotelManagement.Entity.ContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ContactRepos extends JpaRepository<ContactDetails,Long> {
}
