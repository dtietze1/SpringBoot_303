package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    TaskRepository repos1;

    @RequestMapping("/")
    public String listTasks(Model mod1){
        mod1.addAttribute("tasks", repos1.findAll());
        return "list";
    }
    @GetMapping("/add")
    public String TaskForm(Model mod1){
        mod1.addAttribute("task", new Task());
        return "taskform";
    }
    @PostMapping("/process")
    public String processForm(@Valid Task task, BindingResult result) {
        if (result.hasErrors()) {
            return "taskform";
        }
        repos1.save(task);
        return "redirect:/";
    }
//    @RequestMapping("/detail/{id}")
//    public String showCourse(@PathVariable("id") long id, Model mod1){
//        mod1.addAttribute("course", repos1.findById(id).get());
//        return "show";
//    }
    @RequestMapping("/update/{id}")
    public String updateTask(@PathVariable("id") long id, Model mod1) {
        mod1.addAttribute("task", repos1.findById(id).get());
        return "taskform";
    }
    @RequestMapping("/delete/{id}")
    public String delTask(@PathVariable("id") long id) {
        repos1.deleteById(id);
        return "redirect:/";
    }
}
