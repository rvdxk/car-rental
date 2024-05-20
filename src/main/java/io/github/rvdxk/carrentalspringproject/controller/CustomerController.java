package io.github.rvdxk.carrentalspringproject.controller;

import io.github.rvdxk.carrentalspringproject.dto.CustomerDto;
import io.github.rvdxk.carrentalspringproject.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@AllArgsConstructor
public class CustomerController {

    public CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){
        List<CustomerDto> customerDtoList = customerService.getAllCustomers();
        return new ResponseEntity<>(customerDtoList, HttpStatus.OK);
    }

    @PostMapping("/customer/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String createCustomer(@RequestBody @Valid CustomerDto customerDto){
        customerService.createCustomer(customerDto);
        return "Customer successfully created!";
    }

    @GetMapping("/customer/{CustomerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("customerId") Long customerId){
        CustomerDto customerDto = customerService.getCustomerById(customerId);
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

    @PutMapping("customer/{customerId}")
    public String updateCustomer(@PathVariable("customerId") Long customerId,
                               @RequestBody @Valid CustomerDto customerDto){
        customerDto.setId(customerId);
        customerService.updateCustomer(customerDto, customerId);
        return "Customer successfully updated!";
    }

    @DeleteMapping("customers/{customerId}")
    public void deleteCustomer(@PathVariable("customerId")Long customerId){
        customerService.deleteCustomer(customerId);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;

    }
}
