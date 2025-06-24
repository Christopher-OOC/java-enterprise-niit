package com.javalord.welcome.repository;

import com.javalord.welcome.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Integer> {

//    @Query(value = "SELECT * FROM roles WHERE id = ?1 AND name = ?2", nativeQuery = true)
//
//    @Query(value = "SELECT r FROM Role r WHERE r.id = ?1 AND r.name = ?2")

    Role findById(int id);

    Role findByName(String name);


}
