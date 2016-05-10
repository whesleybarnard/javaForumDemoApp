package entelect.spring.example.dao;

import entelect.spring.example.dao.generic.GenericDao;
import entelect.spring.example.dao.model.Customer;
import entelect.spring.example.dao.model.OrderLine;

public interface OrderLineDao extends GenericDao<OrderLine, Long> {

}
