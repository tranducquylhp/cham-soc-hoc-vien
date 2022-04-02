package com.codegym.demo_chatbot_fb.controller;

import com.codegym.demo_chatbot_fb.model.Student;
import com.codegym.demo_chatbot_fb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping()
    public ModelAndView codeList(@RequestParam("s") Optional<String> s, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("student/list");
        if (s.isPresent()){
            modelAndView.addObject("students", studentService.searchStudentByPhoneNumber(s.get()));
        } else modelAndView.addObject("students", studentService.findAll());
        if (request.getParameter("message")!= null){
            modelAndView.addObject("message",request.getParameter("message"));
        }

        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createForm(){
        ModelAndView modelAndView = new ModelAndView("student/create");
        modelAndView.addObject("student", new Student());

        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute Student student){
        String message = studentService.save(student);
        ModelAndView modelAndView;
        if (StringUtils.hasText(message)) {
            modelAndView = new ModelAndView("student/create");
            modelAndView.addObject("message", message);
        } else {
            modelAndView = new ModelAndView("redirect:/students");
            modelAndView.addObject("message", "Create done");
        }

        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editForm(@PathVariable Long id){
        ModelAndView modelAndView;
        Optional<Student> studentOptional = studentService.findById(id);
        if (studentOptional.isPresent()) {
            modelAndView = new ModelAndView("student/edit");
            modelAndView.addObject("student", studentOptional.get());
        } else modelAndView = new ModelAndView("error");

        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView edit(@ModelAttribute Student student, @PathVariable Long id){
        ModelAndView modelAndView;
        String message = studentService.save(student);
        if (StringUtils.hasText(message)) {
            modelAndView = new ModelAndView("student/edit/" + id);
            modelAndView.addObject("message", message);
        } else {
            modelAndView = new ModelAndView("redirect:/students");
            modelAndView.addObject("message", "Sửa thông tin thành công");
        }

        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteForm(@PathVariable Long id){
        ModelAndView modelAndView;
        Optional<Student> studentOptional = studentService.findById(id);
        if (studentOptional.isPresent()) {
            modelAndView = new ModelAndView("student/delete");
            modelAndView.addObject("student", studentOptional.get());
        } else modelAndView = new ModelAndView("error");

        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("redirect:/students");
        modelAndView.addObject("message", "Xoá thành công");
        studentService.delete(id);

        return modelAndView;
    }

    @GetMapping("/codeExercise/view/{id}")
    public ModelAndView view(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("student/view");
        Optional<Student> studentOptional = studentService.findById(id);
        studentOptional.ifPresent(student -> modelAndView.addObject("student", student));

        return modelAndView;
    }
}
