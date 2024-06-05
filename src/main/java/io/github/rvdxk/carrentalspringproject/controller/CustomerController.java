package io.github.rvdxk.carrentalspringproject.controller;

import io.github.rvdxk.carrentalspringproject.dto.CustomerDto;
import io.github.rvdxk.carrentalspringproject.entity.Feedback;
import io.github.rvdxk.carrentalspringproject.mapper.CustomerMapper;
import io.github.rvdxk.carrentalspringproject.service.CustomerService;
import io.github.rvdxk.carrentalspringproject.service.FeedbackService;
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
    private FeedbackService feedbackService;

    @PostMapping("/customers/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String addCustomer(@RequestBody @Valid CustomerDto customerDto){
        customerService.addCustomer(customerDto);
        return "Customer successfully added!";
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){
        List<CustomerDto> customerDtoList = customerService.getAllCustomers();
        return new ResponseEntity<>(customerDtoList, HttpStatus.OK);
    }

    @GetMapping("/customers/{id}")
    public CustomerDto getCustomerById(@PathVariable("id") Long customerId){
        CustomerDto customerDto = customerService.getCustomerById(customerId);
        return customerDto;
    }

    @PutMapping("/customers/{id}")
    public String updateCustomer(@PathVariable("id") Long customerId,
                               @RequestBody @Valid CustomerDto customerDto){
        customerDto.setId(customerId);
        customerService.updateCustomer(customerDto, customerId);
        return "Customer successfully updated!";
    }

    @DeleteMapping("/customers/{id}")
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

    @PostMapping("/customers/{id}/feedback/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String addFeedback(@PathVariable("id") Long customerId,
                              @RequestBody @Valid Feedback feedback){
        CustomerDto customerDto = customerService.getCustomerById(customerId);
        feedback.setCustomer(CustomerMapper.mapToCustomer(customerDto));
        feedbackService.addFeedback(feedback);
        return "Feedback successfully added!";
    }
    @GetMapping("/customers/feedback/all")
    public ResponseEntity<List<Feedback>> getAllFeedback(){
        List<Feedback> feedbackList = feedbackService.getAllFeedback();
        return new ResponseEntity<>(feedbackList,HttpStatus.OK);
    }

    @PutMapping("/customers/{customerId}/feedback/{feedbackId}")
    public String editFeedback(@PathVariable("customerId") Long customerId,
                               @PathVariable("feedbackId") Long feedbackId,
                               @RequestBody @Valid Feedback feedback){
        customerService.getCustomerById(customerId);
        feedbackService.editFeedback(feedback, feedbackId);
        return "Feedback successfully edited!";
    }

    @DeleteMapping("/customers/{customerId}/feedback/{feedbackId}")
    public String deleteFeedback(@PathVariable("customerId") Long customerId,
                                 @PathVariable("feedbackId") Long feedbackId){
        customerService.getCustomerById(customerId);
        feedbackService.deleteFeedback(feedbackId);
        return "Feedback successfully deleted!";
    }
}
