package entelect.spring.example.service;

import entelect.spring.example.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    CustomerDto fetchCustomer(Long id);
}
