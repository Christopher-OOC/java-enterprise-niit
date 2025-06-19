package com.javalord.welcome.repository;

import com.javalord.welcome.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<User, Integer> {

   User findByEmail(String email);

}