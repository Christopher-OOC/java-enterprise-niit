package com.javalord.welcome.repository;

import com.javalord.welcome.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
