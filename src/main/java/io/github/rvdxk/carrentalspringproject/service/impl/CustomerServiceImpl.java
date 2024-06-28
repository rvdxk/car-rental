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
    public void addCustomer(Customer customer, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));

        if(user.getCustomer() != null) {

            customerRepository.delete(user.getCustomer());
        }
        customerRepository.save(customer);
        user.setCustomer(customer);
        userRepository.save(user);
        }


    @Override
    public CustomerDto findCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Customer with id " + id + " not found"));
        return CustomerMapper.mapToCustomerDto(customer);
    }

    @Override
    public void updateCustomer(CustomerDto customerDto, Long id) {
         customerRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Customer with id " + id + " not found"));
        customerDto.setId(id);

        customerRepository.save(CustomerMapper.mapToCustomer(customerDto));

    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Customer with id " + id + " not found"));

        customerRepository.deleteById(id);
    }
}
