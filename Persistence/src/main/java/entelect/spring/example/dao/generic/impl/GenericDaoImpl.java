package entelect.spring.example.dao.generic.impl;


import entelect.spring.example.dao.generic.GenericDao;
import entelect.spring.example.dao.generic.finder.FinderExecutor;
import entelect.spring.example.dao.generic.finder.FinderNamingStrategy;
import entelect.spring.example.dao.generic.finder.impl.SimpleFinderNamingStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unchecked")
public class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK>, FinderExecutor<T> {

    @PersistenceContext
    private EntityManager entityManager;

    private FinderNamingStrategy namingStrategy = new SimpleFinderNamingStrategy();

    private Class<T> type;

    public GenericDaoImpl(final Class<T> aType) {
        this.type = aType;
    }


    public GenericDaoImpl() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public final void create(final T o) {
        getEntityManager().persist(o);
    }

    public final T read(final PK id) {
        return getEntityManager().find(type, id);
    }

    public final void update(final T o) {
        getEntityManager().merge(o);
    }

    public final void delete(final T o) {
        getEntityManager().remove(o);
    }

    public final List<T> executeFinder(final Method method, final Object[] queryArgs) {
        final String queryName = getNamingStrategy().queryNameFromMethod(type, method);
        final Query namedQuery = getEntityManager().createNamedQuery(queryName);
        final String queryString = getHibernateQueryString(namedQuery);
        final String[] parameters = findParameters(queryString);
        if (parameters.length != 0) {
            if (parameters.length != queryArgs.length) {
                throw new RuntimeException(String.format("Incorrect number for arguments for: [%s]", namedQuery.toString()));
            }
            setNamedParams(parameters, queryArgs, namedQuery);
        }
        return namedQuery.getResultList();
    }

    public final <C> C executeSingleResult(final Method method, final Object[] queryArgs, final Class<C> returnType) {
        final String queryName = getNamingStrategy().queryNameFromMethod(type, method);
        final TypedQuery typedQuery = getEntityManager().createNamedQuery(queryName, returnType);
        final String queryString = getHibernateQueryString(typedQuery);
        final String[] parameters = findParameters(queryString);
        if (parameters.length != 0) {
            if (parameters.length != queryArgs.length) {
                throw new RuntimeException(String.format("Incorrect number for arguments for: [%s]", typedQuery.toString()));
            }
            setNamedParams(parameters, queryArgs, typedQuery);
        }
        return (C) typedQuery.getSingleResult();
    }

    private String getHibernateQueryString(final Query aQuery) {
        return aQuery.unwrap(org.hibernate.Query.class).getQueryString();
    }

    private void setNamedParams(final String[] parameters, final Object[] queryArgs, final Query namedQuery) {
        if (queryArgs != null) {
            for (int i = 0; i < parameters.length; i++) {
                String parameter = parameters[i];
                Object argument = queryArgs[i];
                doSetParams(parameter, namedQuery, argument);
            }
        }
    }

    private String[] findParameters(final String query) {

        List<String> list = new ArrayList<String>(0);
        Pattern pattern = Pattern.compile("[:].[\\w]*\\b");
        Matcher matcher = pattern.matcher(query);
        while (matcher.find()) {
            String group = matcher.group().substring(1);
            if (!list.contains(group)) {
                list.add(group);
            }
        }
        String[] names = new String[list.size()];
        return list.toArray(names);
    }

    private void doSetParams(final String parameter, final Query namedQuery, final Object aArg) {
        namedQuery.setParameter(parameter, aArg);
    }

    public final EntityManager getEntityManager() {
        return entityManager;
    }

    public final FinderNamingStrategy getNamingStrategy() {
        return namingStrategy;
    }

    public final void setNamingStrategy(final FinderNamingStrategy aNamingStrategy) {
        namingStrategy = aNamingStrategy;
    }

}
