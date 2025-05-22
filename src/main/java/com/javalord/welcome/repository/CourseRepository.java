package com.javalord.welcome.repository;

import com.javalord.welcome.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}
