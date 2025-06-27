package com.javalord.welcome.controller;

import com.javalord.welcome.model.Course;
import com.javalord.welcome.service.CourseService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminController {

    private CourseService courseService;

    public AdminController(CourseService courseService) {
        this.courseService = courseService;
    }


    @GetMapping(value = "/admin-courses")
    @Secured({"ROLE_ADMIN", "tyuiu"})
    @PostAuthorize("hasRole('ADMIN') || hasAuthority('ROLE_ADMIN') ")
    @PreAuthorize("")
    public String showAndCreateCourses(Model model) {
        List<Course> courses = courseService.getAllCourses();

        model.addAttribute("courses", courses);
        model.addAttribute("newCourse", new Course());

        return "courses";
    }

    @PostMapping(value = "/admin-courses")
    public String addNewCourse(@ModelAttribute("newCourse") Course course) {
        courseService.addCourse(course);

        return "redirect:/admin-courses";
    }

}
