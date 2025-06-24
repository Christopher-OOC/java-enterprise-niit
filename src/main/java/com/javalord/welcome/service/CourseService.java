package com.javalord.welcome.service;

import com.javalord.welcome.model.Course;
import com.javalord.welcome.model.User;
import com.javalord.welcome.repository.CourseRepository;
import com.javalord.welcome.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private CourseRepository courseRepository;
    private UserRepository userRepository;

    public CourseService(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public void addCourse(Course course) {
        courseRepository.save(course);
    }
    
    public void enrollForACourse(String username, int courseId) {
        Course course = courseRepository.findById(courseId).get();
        User user = userRepository.findByEmail(username);

        user.getCourses().add(course);

        userRepository.save(user);
    }
}
