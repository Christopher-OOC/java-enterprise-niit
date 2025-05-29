package com.javalord.welcome.controller;

import com.javalord.welcome.model.Course;
import com.javalord.welcome.model.Student;
import com.javalord.welcome.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/")
    public String displayWelcomePage(Model model) {

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
}
