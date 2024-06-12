package io.github.rvdxk.carrentalspringproject.mapper;

import io.github.rvdxk.carrentalspringproject.dto.CustomerDto;
import io.github.rvdxk.carrentalspringproject.entity.Customer;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(Customer customer){
        return CustomerDto.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .idCardNumber(customer.getIdCardNumber())
                .phoneNumber(customer.getPhoneNumber())
                .dateOfBirth(customer.getDateOfBirth())
                .street(customer.getStreet())
                .houseNumber(customer.getHouseNumber())
                .apartmentNumber(customer.getApartmentNumber())
                .city(customer.getCity())
                .postalCode(customer.getPostalCode())
                .country(customer.getCountry())
                .build();
    }

    public static Customer mapToCustomer(CustomerDto customerDto){
        return Customer.builder()
                .id(customerDto.getId())
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .idCardNumber(customerDto.getIdCardNumber())
                .phoneNumber(customerDto.getPhoneNumber())
                .dateOfBirth(customerDto.getDateOfBirth())
                .street(customerDto.getStreet())
                .houseNumber(customerDto.getHouseNumber())
                .apartmentNumber(customerDto.getApartmentNumber())
                .city(customerDto.getCity())
                .postalCode(customerDto.getPostalCode())
                .country(customerDto.getCountry())
                .build();
    }

}
