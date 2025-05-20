package com.javalord.welcome;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {

    @GetMapping(value = "/welcome")
    public String displayWelcomePage(Model model) {

        Student student = new Student();
        student.setId(1);
        student.setFullName("John Doe");
        student.setLevel("LEVEL_1");

        model.addAttribute("student", student);

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

        System.out.println(student);

        return "redirect:/welcome";
    }

    @GetMapping(value = "students")
    public String getAllStudents(Model model) {



    }



}
