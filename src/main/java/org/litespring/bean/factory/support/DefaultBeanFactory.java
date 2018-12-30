package org.litespring.bean.factory.support;

import org.litespring.bean.BeanDefinition;
import org.litespring.bean.factory.BeanCreateException;
import org.litespring.bean.factory.BeanFactory;
import org.litespring.utils.ClassUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缺省的BeanFactory
 * @author : Lin Can
 * @date : 2018/12/10 20:59
 */
public class DefaultBeanFactory implements BeanDefinitionRegistry,BeanFactory {

    /**
     * 存放id和对应的bean定义
     */
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

    /**
     * 根据beanId获取定义
     * @param beanId beanId
     * @return id 对应的bean 定义
     */
    public BeanDefinition getBeanDefinition(String beanId) {
        return beanDefinitionMap.get(beanId);
    }

    public Object getBean(String beanId) {
        ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
        BeanDefinition definition = getBeanDefinition(beanId);
        if (definition == null) {
            throw new BeanCreateException("创建bean失败：beanDefinition is null");
        }
        String beanClassName = definition.getBeanClassName();
        try {
            Class beanClass = classLoader.loadClass(beanClassName);
            return beanClass.newInstance();
        } catch (Exception e) {
            throw new BeanCreateException("创建bean失败：",beanClassName,e);
        }
    }

    public void registerBeanDefinition(String id,BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(id,beanDefinition);
    }
}
