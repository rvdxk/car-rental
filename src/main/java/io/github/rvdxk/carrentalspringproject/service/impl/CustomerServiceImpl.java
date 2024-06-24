package io.github.rvdxk.carrentalspringproject.service.impl;

import io.github.rvdxk.carrentalspringproject.dto.CustomerDto;
import io.github.rvdxk.carrentalspringproject.entity.Customer;
import io.github.rvdxk.carrentalspringproject.entity.User;
import io.github.rvdxk.carrentalspringproject.exception.ResourceNotFoundException;
import io.github.rvdxk.carrentalspringproject.mapper.CustomerMapper;
import io.github.rvdxk.carrentalspringproject.repository.CustomerRepository;
import io.github.rvdxk.carrentalspringproject.repository.UserRepository;
import io.github.rvdxk.carrentalspringproject.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;



    @Override
    public List<CustomerDto> findAllCustomers() {
        List<Customer> customersList = customerRepository.findAll();
        List<CustomerDto> customersDtoList = customersList.stream()
                .map((customer) -> CustomerMapper.mapToCustomerDto(customer))
                .collect(Collectors.toUnmodifiableList());
        return customersDtoList;
    }

    @Override
    public void addCustomer(CustomerDto customerDto, Long customerId) {
        User user = userRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with id " + customerId + " not found"));

        Customer customer = CustomerMapper.mapToCustomer(customerDto);

        if(user.getCustomer() != null) {

            customerRepository.delete(user.getCustomer());
        }
        customerRepository.save(customer);
        user.setCustomer(customer);
        userRepository.save(user);
        }


    @Override
    public CustomerDto findCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new ResourceNotFoundException("Customer with id " + customerId + " not found"));
        return CustomerMapper.mapToCustomerDto(customer);
    }

    @Override
    public void updateCustomer(CustomerDto customerDto, Long customerId) {
        customerRepository.findById(customerId)
                .orElseThrow(()-> new ResourceNotFoundException("Customer with id " + customerId + " not found"));

        customerRepository.save(CustomerMapper.mapToCustomer(customerDto));

    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.findById(customerId)
                .orElseThrow(()-> new ResourceNotFoundException("Customer with id " + customerId + " not found"));

        customerRepository.deleteById(customerId);
    }
}
