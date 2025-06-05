package com.javalord.welcome.controller;

import com.javalord.welcome.model.Course;
import com.javalord.welcome.model.Student;
import com.javalord.welcome.service.CourseService;
import com.javalord.welcome.service.StudentService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    private StudentService studentService;
    private CourseService courseService;

    public StudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping(value = "/")
    public String displayWelcomePage(@AuthenticationPrincipal User user,  Model model) {

        model.addAttribute("name", user.getUsername());

        return "welcome";
    }

    @GetMapping(value = "/create")
    public String showCreatePage(Model model) {
        Student newStudent = new Student();

        model.addAttribute("newStudent", newStudent);

        return "createAccount";
    }

    @PostMapping(value = "/create")
    public String createStudent(@ModelAttribute("newStudent") Student student) {

        studentService.saveStudent(student);

        return "redirect:/students";
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "loginPage";
    }


    @GetMapping(value = "/students")
    public String getAllStudents(Model model) {
        List<Student> students = studentService.getAllStudents();

        model.addAttribute("students", students);

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
            @AuthenticationPrincipal User user,
            @PathVariable("courseId") int courseId) {
        courseService.enrollForACourse(user.getUsername(), courseId);

        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);

        return "redirect:/enroll-course";
    }


}
