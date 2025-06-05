package com.javalord.welcome.service;

import com.javalord.welcome.model.Course;
import com.javalord.welcome.model.Student;
import com.javalord.welcome.repository.CourseRepository;
import com.javalord.welcome.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Student student = studentRepository.findByEmail(username);

        student.getCourses().add(course);

        studentRepository.save(student);
    }
}
