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

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public String addCustomer(@Valid @RequestBody Customer cust) {

        customerRepo.save(cust);
        return "redirect:/list";
    }
    @GetMapping("/add")
    public String showForm(){
        return "add-customer";
    }

    @RequestMapping("/list")
    public String getCust(Model model) {
        model.addAttribute("customers", customerRepo.findAll());
        return "index";
    }
}
