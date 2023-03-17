package com.example.seoulcultureport.repository;

import com.example.seoulcultureport.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
