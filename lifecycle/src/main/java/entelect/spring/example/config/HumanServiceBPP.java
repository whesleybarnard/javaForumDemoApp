package entelect.spring.example.config;

import entelect.spring.example.beans.HumanService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class HumanServiceBPP implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        if(o instanceof HumanService) {
            HumanService humanService = (HumanService)o;
            humanService.getPets().add("bird -- BPP");
        }

        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        if(o instanceof HumanService) {
            HumanService humanService = (HumanService)o;
            humanService.getPets().add("monkey -- BPP");
        }

        return o;
    }
}
