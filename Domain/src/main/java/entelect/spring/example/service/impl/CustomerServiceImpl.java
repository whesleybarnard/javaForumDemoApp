package entelect.spring.example.service.impl;

import entelect.spring.example.dao.CustomerDao;
import entelect.spring.example.dao.model.Customer;
import entelect.spring.example.dto.CustomerDto;
import entelect.spring.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerServiceImpl implements CustomerService {

    //Field injection using the autowired annotation
    @Autowired
    private CustomerDao customerDao;

    @Override
    public CustomerDto fetchCustomer(Long id) {
        Customer customer = customerDao.read(id);

        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setSurname(customer.getSurname());
        customerDto.setIdNumber(customer.getIdNumber());

        return customerDto;
    }
}
