package com.javalord.welcome.service;

import com.javalord.welcome.model.Course;
import com.javalord.welcome.model.User;
import com.javalord.welcome.repository.CourseRepository;
import com.javalord.welcome.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private CourseRepository courseRepository;
    private StudentRepository studentRepository;

    public CourseService(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public void addCourse(Course course) {
        courseRepository.save(course);
    }
    
    public void enrollForACourse(String username, int courseId) {
        Course course = courseRepository.findById(courseId).get();
        User user = studentRepository.findByEmail(username);

        user.getCourses().add(course);

        studentRepository.save(user);
    }
}
