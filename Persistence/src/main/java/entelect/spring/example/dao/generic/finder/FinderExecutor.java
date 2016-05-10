package entelect.spring.example.dao.generic.finder;

import java.lang.reflect.Method;
import java.util.List;

public interface FinderExecutor<T> {

    List<T> executeFinder(Method method, Object[] queryArgs);

    <C> C executeSingleResult(Method method, Object[] queryArgs, Class<C> returnType);
}
