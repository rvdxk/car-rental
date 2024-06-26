package io.github.rvdxk.carrentalspringproject.service;

import io.github.rvdxk.carrentalspringproject.dto.CustomerDto;
import io.github.rvdxk.carrentalspringproject.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> findAllCustomers();
    void addCustomer(Customer customer, Long id);
    CustomerDto findCustomerById(Long id);
    void updateCustomer(CustomerDto customerDto, Long id);
    void deleteCustomer(Long id);
}
