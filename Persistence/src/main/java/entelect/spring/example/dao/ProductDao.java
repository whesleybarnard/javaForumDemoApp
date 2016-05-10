package entelect.spring.example.dao;

import entelect.spring.example.dao.generic.GenericDao;
import entelect.spring.example.dao.model.Product;

public interface ProductDao extends GenericDao<Product, Long> {
    
}
