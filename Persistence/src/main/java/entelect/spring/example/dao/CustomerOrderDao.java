package entelect.spring.example.dao;

import entelect.spring.example.dao.generic.GenericDao;
import entelect.spring.example.dao.model.CustomerOrder;

public interface CustomerOrderDao extends GenericDao<CustomerOrder, Long> {

}
