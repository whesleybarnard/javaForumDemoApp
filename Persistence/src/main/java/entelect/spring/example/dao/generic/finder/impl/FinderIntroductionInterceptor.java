package entelect.spring.example.dao.generic.finder.impl;


import entelect.spring.example.dao.generic.finder.FinderExecutor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.IntroductionInterceptor;

import java.util.List;

public class FinderIntroductionInterceptor implements IntroductionInterceptor {

    public final Object invoke(final MethodInvocation methodInvocation) throws Throwable {
        String methodName = methodInvocation.getMethod().getName();
        if (shouldProceedWithInvocation(methodName)) {
            return methodInvocation.proceed();
        } else {
            return processFinderOrSingleResultExecutionMethod(methodInvocation);
        }
    }

    private boolean shouldProceedWithInvocation(final String methodName) {
        return "create".equalsIgnoreCase(methodName) || "read".equalsIgnoreCase(methodName) || "update".equalsIgnoreCase(methodName)
                || "delete".equalsIgnoreCase(methodName) || "createAndDoNotSetTenantId".equalsIgnoreCase(methodName);
    }

    private Object processFinderOrSingleResultExecutionMethod(final MethodInvocation aMethodInvocation) {
        Object[] arguments = aMethodInvocation.getArguments();
        FinderExecutor executor = (FinderExecutor) aMethodInvocation.getThis();
        if (aMethodInvocation.getMethod().getReturnType().equals(List.class)) {
            return executor.executeFinder(aMethodInvocation.getMethod(), arguments);
        } else {
            return executor.executeSingleResult(aMethodInvocation.getMethod(), arguments, aMethodInvocation.getMethod().getReturnType());
        }
    }

    public final boolean implementsInterface(final Class intf) {
        return intf.isInterface() && FinderExecutor.class.isAssignableFrom(intf);
    }
}

