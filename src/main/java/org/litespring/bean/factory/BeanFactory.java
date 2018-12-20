package org.litespring.bean.factory;

/**
 * 创建bean的工厂
 * @author : Lin Can
 * @date : 2018/12/10 20:59
 */
public interface BeanFactory {

    /**
     * 获取bean
     * @param beanId
     * @return
     */
    Object getBean(String beanId);
}
