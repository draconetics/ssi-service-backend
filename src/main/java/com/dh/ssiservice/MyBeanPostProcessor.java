/**
 * @author: Edson A. Terceros T.
 */

package com.dh.ssiservice;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof SpringBeanLifeCycleDemo) {
            ((SpringBeanLifeCycleDemo) bean).beforeInit();
            System.out.println("bean name: " + beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof SpringBeanLifeCycleDemo) {
            ((SpringBeanLifeCycleDemo) bean).afterInit();
        }
        return bean;
    }
}
