package io.github.rvdxk.carrentalspringproject.service.impl;

import io.github.rvdxk.carrentalspringproject.dto.CustomerDto;
import io.github.rvdxk.carrentalspringproject.entity.Customer;
import io.github.rvdxk.carrentalspringproject.exception.ResourceNotFoundException;
import io.github.rvdxk.carrentalspringproject.mapper.CustomerMapper;
import io.github.rvdxk.carrentalspringproject.repository.CustomerRepository;
import io.github.rvdxk.carrentalspringproject.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customersList = customerRepository.findAll();
        List<CustomerDto> customersDtoList = customersList.stream()
                .map((customer) -> CustomerMapper.mapToCustomerDto(customer))
                .collect(Collectors.toUnmodifiableList());
        return customersDtoList;
    }

    @Override
    public void addCustomer(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto);
        customerRepository.save(customer);
    }

    @Override
    public CustomerDto getCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new ResourceNotFoundException("Customer with id " + customerId + " not found"));
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer);
        return customerDto;
    }

    @Override
    public void updateCustomer(CustomerDto customerDto, Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new ResourceNotFoundException("Customer with id " + customerId + " not found"));

        customerRepository.save(CustomerMapper.mapToCustomer(customerDto));

    }

    @Override
    public void deleteCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new ResourceNotFoundException("Customer with id " + customerId + " not found"));

        customerRepository.deleteById(customerId);
    }
}
