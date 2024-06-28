package io.github.rvdxk.carrentalspringproject.service.impl;

import io.github.rvdxk.carrentalspringproject.constant.Country;
import io.github.rvdxk.carrentalspringproject.dto.CustomerDto;
import io.github.rvdxk.carrentalspringproject.entity.Customer;
import io.github.rvdxk.carrentalspringproject.entity.Role;
import io.github.rvdxk.carrentalspringproject.entity.User;
import io.github.rvdxk.carrentalspringproject.repository.CustomerRepository;
import io.github.rvdxk.carrentalspringproject.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private Customer customer1;
    private Customer customer2;

    private User user;


    @BeforeEach
    void setUp() throws ParseException {

        user = User.builder()
                .id(1L)
                .firstName("admin")
                .lastName("admin")
                .email("admin@admin.com")
                .password("admin")
                .role(Role.ADMIN)
                .build();

        customer1 = Customer.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .idCardNumber("AAA 90234")
                .phoneNumber("+48 777 777 777")
                .dateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1992-02-22"))
                .street("Grunwaldzka")
                .houseNumber(122)
                .apartmentNumber(20)
                .city("Gdansk")
                .postalCode("80-800")
                .country(Country.POLAND)
                .user(user)
                .build();

        customer2 = Customer.builder()
                .id(2L)
                .firstName("Jane")
                .lastName("Doe")
                .idCardNumber("BBB 90234")
                .phoneNumber("+48 999 999 999")
                .dateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1993-03-23"))
                .street("Grunwaldzka")
                .houseNumber(133)
                .apartmentNumber(30)
                .city("Gdansk")
                .postalCode("80-800")
                .country(Country.POLAND)
                .user(user)
                .build();
    }

    @Test
    void findAllCustomers_ShouldReturnCustomersList() {

        List<Customer> customerList = Arrays.asList(customer1, customer2);
        when(customerRepository.findAll()).thenReturn(customerList);

        List<CustomerDto> result = customerService.findAllCustomers();

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(customerRepository, times(1)).findAll();

        assertEquals(customer1.getFirstName(), result.get(0).getFirstName());
        assertEquals(customer1.getLastName(), result.get(0).getLastName());
        assertEquals(customer2.getFirstName(), result.get(1).getFirstName());
        assertEquals(customer2.getLastName(), result.get(1).getLastName());
    }

    @Test
    void addCustomer_ShouldSaveCustomerInformationToUserAccount() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(customerRepository.save(customer1)).thenReturn(customer1);

        customerService.addCustomer(customer1, user.getId());

        verify(customerRepository,times(1)).save(customer1);
    }

    @Test
    void findCustomerById_ShouldReturnCustomerByIdWithFullInformation() {
        when(customerRepository.findById(customer1.getId())).thenReturn(Optional.of(customer1));

        CustomerDto result = customerService.findCustomerById(customer1.getId());

        assertNotNull(result);

        verify(customerRepository, times(1)).findById(customer1.getId());

        assertEquals(customer1.getFirstName(), result.getFirstName());
        assertEquals(customer1.getLastName(), result.getLastName());
        assertEquals(customer1.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(customer1.getDateOfBirth(), result.getDateOfBirth());
    }

    @Test
    void updateCustomer() throws ParseException {

        CustomerDto updateCustomerDto = CustomerDto.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .idCardNumber("CCC 90234")
                .phoneNumber("+48 000 000 000")
                .dateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1990-01-21"))
                .street("Grunwaldzka")
                .houseNumber(111)
                .apartmentNumber(10)
                .city("Gdansk")
                .postalCode("80-800")
                .country(Country.POLAND)
                .build();

        when(customerRepository.findById(customer1.getId())).thenReturn(Optional.of(customer1));

        customerService.updateCustomer(updateCustomerDto, customer1.getId());

        verify(customerRepository, times(1)).findById(customer1.getId());

        verify(customerRepository, times(1)).save(any(Customer.class));

        ArgumentCaptor<Customer> customerCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository).save(customerCaptor.capture());
        Customer updatedCustomer = customerCaptor.getValue();

        assertEquals(updateCustomerDto.getFirstName(), updatedCustomer.getFirstName());
        assertEquals(updateCustomerDto.getLastName(), updatedCustomer.getLastName());
        assertEquals(updateCustomerDto.getPhoneNumber(), updatedCustomer.getPhoneNumber());
        assertEquals(updateCustomerDto.getDateOfBirth(), updatedCustomer.getDateOfBirth());
    }

    @Test
    void deleteCustomer() {
        when(customerRepository.findById(customer1.getId())).thenReturn(Optional.of(customer1));

        customerService.deleteCustomer(customer1.getId());

        verify(customerRepository, times(1)).findById(customer1.getId());
        verify(customerRepository, times(1)).deleteById(customer1.getId());
    }
}