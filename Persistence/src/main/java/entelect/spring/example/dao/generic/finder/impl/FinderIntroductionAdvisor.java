package entelect.spring.example.dao.generic.finder.impl;


import entelect.spring.example.dao.generic.finder.FinderExecutor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DefaultIntroductionAdvisor;

/**
 * Custom finder introduction advisor.
 */
public class FinderIntroductionAdvisor extends DefaultIntroductionAdvisor {

    private static final long serialVersionUID = -4665953944876164346L;

    public FinderIntroductionAdvisor() {
        super(new FinderIntroductionInterceptor());
    }

    @Override
    public final ClassFilter getClassFilter() {
        return new GenericDaoClassFilterImpl();
    }

    private static class GenericDaoClassFilterImpl implements ClassFilter {

        public boolean matches(final Class c) {
            return FinderExecutor.class.isAssignableFrom(c);
        }
    }

}
