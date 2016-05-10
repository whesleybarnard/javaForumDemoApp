package entelect.spring.example.dao.config;

import entelect.spring.example.dao.CustomerDao;
import entelect.spring.example.dao.CustomerOrderDao;
import entelect.spring.example.dao.OrderLineDao;
import entelect.spring.example.dao.ProductDao;
import entelect.spring.example.dao.generic.GenericDao;
import entelect.spring.example.dao.generic.finder.impl.FinderIntroductionAdvisor;
import entelect.spring.example.dao.generic.finder.impl.SimpleFinderNamingStrategy;
import entelect.spring.example.dao.generic.impl.GenericDaoImpl;
import entelect.spring.example.dao.model.Customer;
import entelect.spring.example.dao.model.CustomerOrder;
import entelect.spring.example.dao.model.OrderLine;
import entelect.spring.example.dao.model.Product;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

//An example of how to do create the required beans for your DAOs using java bean configuration
@Configuration
@Profile("test")
public class TestPersistenceConfig {

    @Bean
    public ProxyFactoryBean productDao() throws ClassNotFoundException {
        return createDao(Product.class, ProductDao.class);
    }

    @Bean
    public ProxyFactoryBean orderLineDao() throws ClassNotFoundException {
        return createDao(OrderLine.class, OrderLineDao.class);
    }

    @Bean
    public ProxyFactoryBean customerOderDao() throws ClassNotFoundException {
        return createDao(CustomerOrder.class, CustomerOrderDao.class);
    }

    //Constructing the bean without calling teh createDao() method
    @Bean
    public ProxyFactoryBean customerDao() throws ClassNotFoundException {
        ProxyFactoryBean abstractDao = createAbstractDao();
        abstractDao.setProxyInterfaces(new Class[]{CustomerDao.class});

        GenericDaoImpl genericDao = new GenericDaoImpl(Customer.class);
        genericDao.setNamingStrategy(extendedFinderNamingStrategy());

        abstractDao.setTarget(genericDao);

        return abstractDao;
    }

    //An example of how setter injection would work in java config
    @Bean
    public GenericDao genericDao(){
        GenericDaoImpl genericDao = new GenericDaoImpl();

        //using setter injection to inject a bean into the genericDao
        genericDao.setNamingStrategy(extendedFinderNamingStrategy());

        return genericDao;
    }

    @Bean
    public SimpleFinderNamingStrategy extendedFinderNamingStrategy() {
        return new SimpleFinderNamingStrategy();
    }

    @Bean
    public FinderIntroductionAdvisor finderIntroductionAdvisor() {
        return new FinderIntroductionAdvisor();
    }

    private ProxyFactoryBean createDao(Class targetClass, Class proxyClass) throws ClassNotFoundException {
        ProxyFactoryBean abstractDao = createAbstractDao();

        abstractDao.setProxyInterfaces(new Class[]{proxyClass});

        GenericDaoImpl genericDao = new GenericDaoImpl(targetClass);
        genericDao.setNamingStrategy(extendedFinderNamingStrategy());

        abstractDao.setTarget(genericDao);

        return abstractDao;
    }

    private ProxyFactoryBean createAbstractDao() {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();

        proxyFactoryBean.setInterceptorNames("finderIntroductionAdvisor");

        return proxyFactoryBean;
    }
}
