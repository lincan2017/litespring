package org.litespring.bean.factory.support;

import org.litespring.bean.BeanDefinition;
import org.litespring.bean.factory.BeanFactory;
import org.litespring.utils.ClassUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : Lin Can
 * @date : 2018/12/10 20:59
 */
public class DefaultBeanFactory implements BeanDefinitionRegistry,BeanFactory {


    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

    public BeanDefinition getBeanDefinition(String beanId) {
        return beanDefinitionMap.get(beanId);
    }

    public Object getBean(String beanId) {
        ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
        BeanDefinition definition = getBeanDefinition(beanId);
        if (definition == null) {
            throw new RuntimeException("没有对应的bean定义");
        }
        String beanClassName = definition.getBeanClassName();
        try {
            Class beanClass = classLoader.loadClass(beanClassName);
            return beanClass.newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    public void registerBeanDefinition(String id,BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(id,beanDefinition);
    }
}
