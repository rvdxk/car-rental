package io.github.rvdxk.carrentalspringproject.mapper;

import io.github.rvdxk.carrentalspringproject.dto.CustomerDto;
import io.github.rvdxk.carrentalspringproject.entity.Customer;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(Customer customer){
        CustomerDto customerDto = new CustomerDto(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getIdCardNumber(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getDateOfBirth(),
                customer.getStreet(),
                customer.getHouseNumber(),
                customer.getApartmentNumber(),
                customer.getCity(),
                customer.getPostalCode(),
                customer.getCountry()
        );
        return customerDto;
    }

    public static Customer mapToCustomer(CustomerDto customerDto){
        Customer customer = new Customer(
                customerDto.getId(),
                customerDto.getFirstName(),
                customerDto.getLastName(),
                customerDto.getIdCardNumber(),
                customerDto.getEmail(),
                customerDto.getPhoneNumber(),
                customerDto.getDateOfBirth(),
                customerDto.getStreet(),
                customerDto.getHouseNumber(),
                customerDto.getApartmentNumber(),
                customerDto.getCity(),
                customerDto.getPostalCode(),
                customerDto.getCountry()
        );
        return customer;
    }

}
