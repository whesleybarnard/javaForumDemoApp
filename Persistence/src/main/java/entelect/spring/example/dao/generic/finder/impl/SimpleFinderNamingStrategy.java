package entelect.spring.example.dao.generic.finder.impl;


import entelect.spring.example.dao.generic.finder.FinderNamingStrategy;

import java.lang.reflect.Method;

public class SimpleFinderNamingStrategy implements FinderNamingStrategy {

    public final String queryNameFromMethod(final Class findTargetType, final Method finderMethod) {
        return findTargetType.getSimpleName() + "." + finderMethod.getName();
    }

}
