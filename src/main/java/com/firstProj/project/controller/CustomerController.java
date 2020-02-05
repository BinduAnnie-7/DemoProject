package com.firstProj.project.controller;

import com.firstProj.project.model.Customer;
import com.firstProj.project.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class CustomerController {

    @Autowired
    CustomerRepo customerRepo;

    @PostMapping("/add")
    public String addCustomer(@Valid Customer customer, BindingResult result){
        customerRepo.save(customer);
        return "redirect:/list";
    }
    @GetMapping("/add")
    public String showForm(Model model){
        model.addAttribute("customer", new Customer());
        return "add-customer";
    }

    @RequestMapping("/list")
    public String getCust(Model model) {
        model.addAttribute("customers", customerRepo.findAll());
        return "index";
    }
}
