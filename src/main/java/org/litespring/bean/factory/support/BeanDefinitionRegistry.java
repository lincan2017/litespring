package org.litespring.bean.factory.support;

import org.litespring.bean.BeanDefinition;

/**
 * BeanDefinition相关操作的接口
 * @author : Lin Can
 * @date : 2018/12/20 9:08
 */
public interface BeanDefinitionRegistry {
    /**
     * 获取bean定义
     * @param beanId beanId
     * @return
     */
    BeanDefinition getBeanDefinition(String beanId);

    /**
     * 注册bean定义
     * @param id beanId
     * @param beanDefinition bean定义
     */
    void registerBeanDefinition(String id,BeanDefinition beanDefinition);
}
