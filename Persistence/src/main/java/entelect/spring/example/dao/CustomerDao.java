package entelect.spring.example.dao;

import entelect.spring.example.dao.generic.GenericDao;
import entelect.spring.example.dao.model.Customer;

public interface CustomerDao extends GenericDao<Customer, Long> {

}
