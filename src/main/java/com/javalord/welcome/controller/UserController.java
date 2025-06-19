package com.javalord.welcome.controller;

import com.javalord.welcome.model.Course;
import com.javalord.welcome.model.User;
import com.javalord.welcome.service.CourseService;
import com.javalord.welcome.service.StudentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private StudentService studentService;
    private CourseService courseService;

    public UserController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping(value = "/")
    public String displayWelcomePage(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user, Model model) {

        model.addAttribute("name", user.getUsername());

        return "welcome";
    }

    @GetMapping(value = "/create")
    public String showCreatePage(Model model) {
        User newUser = new User();

        model.addAttribute("newUser", newUser);

        return "createAccount";
    }

    @PostMapping(value = "/create")
    public String createStudent(@ModelAttribute("newStudent") User user) {

        studentService.saveStudent(user);

        return "redirect:/students";
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "loginPage";
    }


    @GetMapping(value = "/students")
    public String getAllStudents(Model model) {
        List<User> users = studentService.getAllStudents();

        model.addAttribute("users", users);

        return "listStudent";
    }

    @GetMapping(value = "/enroll-course")
    public String enrollCourse(Model model) {
        List<Course> courses = courseService.getAllCourses();

        model.addAttribute("courses", courses);

        return "enrollCourse";
    }

    @GetMapping(value = "/enroll-course/{courseId}")
    public String enrollCourse(
            Model model,
            @AuthenticationPrincipal org.springframework.security.core.userdetails.User user,
            @PathVariable("courseId") int courseId) {
        courseService.enrollForACourse(user.getUsername(), courseId);

        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);

        return "redirect:/enroll-course";
    }


}
