package entelect.spring.example.service.impl;

import entelect.spring.example.dao.ProductDao;
import entelect.spring.example.dto.ProductDto;
import entelect.spring.example.service.ProductService;
import org.springframework.stereotype.Service;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    public ProductDto fetchProduct() {
        return null;
    }

    //We use this setter to inject the dependency when the bean is created
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }
}
