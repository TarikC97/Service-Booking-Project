package com.tarikc.ServiceBookingSystem.Repository;

import com.tarikc.ServiceBookingSystem.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> findAllByCompanyId(Long companyId);
}
