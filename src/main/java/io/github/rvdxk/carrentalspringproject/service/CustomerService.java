package io.github.rvdxk.carrentalspringproject.service;

import io.github.rvdxk.carrentalspringproject.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAllCustomers();
    void addCustomer(CustomerDto customerDto, Long customerId);
    CustomerDto getCustomerById(Long customerId);
    void updateCustomer(CustomerDto customerDto, Long customerId);
    void deleteCustomer(Long customerId);
}
