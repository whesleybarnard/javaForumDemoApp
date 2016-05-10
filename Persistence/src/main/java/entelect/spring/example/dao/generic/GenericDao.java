package entelect.spring.example.dao.generic;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * The basic GenericDao interface with CRUD methods.Finders are added with interface inheritance and AOP introductions for concrete implementations.
 * Extended interfaces may declare methods starting with find, list, iterate or scroll.
 * They will execute a pre-configured query that is looked up based on the rest of the method name.
 *
 * @param <T>  parameter
 * @param <PK> parameter
 */
public interface GenericDao<T, PK extends Serializable> {

    /**
     * Create.
     *
     * @param newInstance instance
     */
    void create(T newInstance);

    /**
     * Read.
     *
     * @param id id
     * @return T
     */
    T read(PK id);

    /**
     * Update.
     *
     * @param transientObject object
     */
    void update(T transientObject);

    /**
     * Delete.
     *
     * @param persistentObject object
     */
    void delete(T persistentObject);
}

