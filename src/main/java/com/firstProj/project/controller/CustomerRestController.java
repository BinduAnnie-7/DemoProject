package com.firstProj.project.controller;

import com.firstProj.project.model.Customer;
import com.firstProj.project.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerRestController {

    @Autowired
    CustomerRepo customerRepo;

    @PostMapping("/customers")
    public Customer addCustomer(@Valid @RequestBody Customer cust) {

        return customerRepo.save(cust);
    }

    @GetMapping("/customers")
    public List<Customer> getCustData() {
        return customerRepo.findAll();
    }


    @PutMapping("/customers/{id}")
    public Optional<Customer> updateCustomer(@PathVariable(value = "id") Long custid, @Valid @RequestBody Customer cust) {

        return customerRepo.findById(custid).map(customer1 -> {
            customer1.setFirstName(cust.getFirstName());
            customer1.setLastName(cust.getLastName());
            customer1.setAddress(cust.getAddress());
            customer1.setEmailid(cust.getEmailid());
            customer1.setProduct(cust.getProduct());
            return  customerRepo.save(customer1);
        });
    }

    @DeleteMapping("/customers/{id}")
    public Optional<?> deleteCustomer(@PathVariable(value = "id") Long custid)
    {
        return customerRepo.findById(custid).map(customer -> {
            customerRepo.delete(customer);
            return ResponseEntity.ok().build();
        });
    }
}
