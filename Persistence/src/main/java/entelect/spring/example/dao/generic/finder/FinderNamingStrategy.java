package entelect.spring.example.dao.generic.finder;

import java.lang.reflect.Method;

public interface FinderNamingStrategy {

    String queryNameFromMethod(final Class findTargetType, final Method finderMethod);
}
