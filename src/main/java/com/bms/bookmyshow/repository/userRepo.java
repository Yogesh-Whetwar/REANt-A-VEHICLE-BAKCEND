package com.bms.bookmyshow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bms.bookmyshow.model.User;

@Repository
public interface userRepo extends JpaRepository<User,Long>{
    Optional<User> findByEmail(String email);
}
