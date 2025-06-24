package com.javalord.welcome.service;

import com.javalord.welcome.model.Role;
import com.javalord.welcome.model.User;
import com.javalord.welcome.repository.RoleRepository;
import com.javalord.welcome.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;


    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       RoleRepository roleRepository
                          ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public void saveStudent(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role roleStudent = roleRepository.findByName("ROLE_STUDENT");
        user.getRoles().add(roleStudent);

        userRepository.save(user);
    }

    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role roleAdmin = roleRepository.findByName("ROLE_ADMIN");
        user.getRoles().add(roleAdmin);

        userRepository.save(user);
    }

    public List<User> getAllStudents() {
        Role roleStudent = roleRepository.findByName("ROLE_STUDENT");

        return userRepository.findByRolesContaining(roleStudent);
    }

    public List<User> getAllAdmins() {
        Role roleAdmin = roleRepository.findByName("ROLE_ADMIN");

        return userRepository.findByRolesContaining(roleAdmin);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User student = userRepository.findByEmail(username);
        if (student == null) {
            throw new UsernameNotFoundException("User not found!");
        }

        org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(
                student.getEmail(),
                student.getPassword(),
                student.isEnabled(),
                true,
                true,
                true,
                List.of()
        );

        return user;
    }
}
