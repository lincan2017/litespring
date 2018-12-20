package org.litespring.bean.factory.support;

import org.litespring.bean.BeanDefinition;

/**
 * @author : Lin Can
 * @date : 2018/12/20 9:08
 */
public interface BeanDefinitionRegistry {
    /**
     * 获取bean定义
     * @return
     */
    BeanDefinition getBeanDefinition(String beanId);

    /**
     * 注册bean定义
     */
    void registerBeanDefinition(String id,BeanDefinition beanDefinition);
}
