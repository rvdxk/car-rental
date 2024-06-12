package io.github.rvdxk.carrentalspringproject.controller;

import io.github.rvdxk.carrentalspringproject.dto.CustomerDto;
import io.github.rvdxk.carrentalspringproject.service.CustomerService;
import io.github.rvdxk.carrentalspringproject.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    @PostMapping("user/{id}/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public String addCustomer(@RequestBody @Valid CustomerDto customerDto,
                              @PathVariable("id") Long id){
        customerService.addCustomer(customerDto, id);
        return "Customer successfully added!";
    }

    @GetMapping("/customer")
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){
        List<CustomerDto> customerDtoList = customerService.getAllCustomers();
        return new ResponseEntity<>(customerDtoList, HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public CustomerDto getCustomerById(@PathVariable("id") Long customerId){
        CustomerDto customerDto = customerService.getCustomerById(customerId);
        return customerDto;
    }

    @PutMapping("/customer/{id}")
    public String updateCustomer(@PathVariable("id") Long customerId,
                               @RequestBody @Valid CustomerDto customerDto){
        customerDto.setId(customerId);
        customerService.updateCustomer(customerDto, customerId);
        return "Customer successfully updated!";
    }

    @DeleteMapping("/customer/{id}")
    public String deleteCustomer(@PathVariable("id")Long customerId){
        customerService.deleteCustomer(customerId);
        return "Customer successfully deleted!";
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
