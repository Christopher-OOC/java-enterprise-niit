package com.javalord.welcome.controller;

import com.javalord.welcome.model.Course;
import com.javalord.welcome.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(value = "/courses")
    public String getAllCourses(Model model) {
        List<Course> courses = courseService.getAllCourses();

        model.addAttribute("courses", courses);

        return "courses";
    }



}
