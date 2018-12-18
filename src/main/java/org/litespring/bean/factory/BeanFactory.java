package org.litespring.bean.factory;

/**
 * @author : Lin Can
 * @date : 2018/12/10 20:59
 */
public interface BeanFactory {
    BeanDefinition getDefinition(String beanId);

    Object getBean(String beanId);
}
