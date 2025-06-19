package com.javalord.welcome.service;

import com.javalord.welcome.model.User;
import com.javalord.welcome.repository.StudentRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements UserDetailsService {

    private StudentRepository studentRepository;
    private PasswordEncoder passwordEncoder;

    public StudentService(StudentRepository studentRepository,
                          PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveStudent(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user

        studentRepository.save(user);
    }

    public List<User> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User student = studentRepository.findByEmail(username);
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
