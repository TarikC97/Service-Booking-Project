package com.tarikc.ServiceBookingSystem.Repository;

import com.tarikc.ServiceBookingSystem.Entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRepository extends JpaRepository<Ad,Long> {

}
