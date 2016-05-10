package entelect.spring.example.service.impl;

import entelect.spring.example.dao.CustomerOrderDao;
import entelect.spring.example.dao.model.CustomerOrder;
import entelect.spring.example.service.CustomerOrderService;

public class CustomerOrderServiceImpl implements CustomerOrderService {

    private CustomerOrderDao customerOrderDao;

    //User constructor injection to inject the required dependency into the class
    public CustomerOrderServiceImpl(CustomerOrderDao customerOrderDao){
        this.customerOrderDao = customerOrderDao;
    }

    public CustomerOrder fetchCustomerOrder() {
        return null;
    }
}
