package io.github.rvdxk.carrentalspringproject.controller;

import io.github.rvdxk.carrentalspringproject.dto.CustomerDto;
import io.github.rvdxk.carrentalspringproject.entity.Customer;
import io.github.rvdxk.carrentalspringproject.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class CustomerController {


    private final CustomerService customerService;


    @PostMapping("/{id}/customers")
    public ResponseEntity<String> addCustomer(@RequestBody @Valid Customer customer,
                                              @PathVariable("id") Long id){
        customerService.addCustomer(customer, id);
        return new ResponseEntity<>("Customer successfully added!", HttpStatus.CREATED);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDto>> findAllCustomers(){
        List<CustomerDto> customerDtoList = customerService.findAllCustomers();
        return new ResponseEntity<>(customerDtoList, HttpStatus.OK);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDto> findCustomerById(@PathVariable("id") Long customerId){
        CustomerDto customerDto = customerService.findCustomerById(customerId);
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable("id") Long customerId,
                                                 @RequestBody @Valid CustomerDto customerDto){
        customerService.updateCustomer(customerDto, customerId);
        return new ResponseEntity<>("Customer successfully updated!", HttpStatus.OK);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id")Long customerId){
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>("Customer successfully deleted!", HttpStatus.NO_CONTENT);
    }
}
