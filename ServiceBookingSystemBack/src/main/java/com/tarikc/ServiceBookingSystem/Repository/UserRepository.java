package com.tarikc.ServiceBookingSystem.Repository;

import com.tarikc.ServiceBookingSystem.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findFirstByEmail(String email);


}
