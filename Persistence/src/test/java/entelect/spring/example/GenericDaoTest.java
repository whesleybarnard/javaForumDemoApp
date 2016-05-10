package entelect.spring.example;

import entelect.spring.example.dao.CustomerDao;
import entelect.spring.example.dao.model.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ContextConfiguration(value = "classpath*:test-persistence-config.xml")
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class GenericDaoTest {

    @Autowired(required = false)
    @Qualifier("profiledBean")
    private String profiledBean;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CustomerDao customerDao;

    @PersistenceContext(name = "springJavaForum")
    private EntityManager entityManager;

    @Test
    @Transactional
    public void testSomething() {
        Customer customer = customerDao.read(100000L);

        System.out.println(customer);
        System.out.println("profiledBean : " + profiledBean);

        Assert.assertNotNull(customer);
    }

    @Test
    @Transactional
    public void testSomething_error() {

        try {
            jdbcTemplate.execute("select * from ABC");
        }
        catch (DataAccessException e) {
            System.out.println("DataAccessException : " + (e instanceof DataAccessException));
            System.out.println("BadSqlGrammarException : " + (e instanceof BadSqlGrammarException));
            System.out.println("DataIntegrityViolationException : " + (e instanceof DataIntegrityViolationException));
        }
    }
}
