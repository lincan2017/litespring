package org.litespring.bean.factory.support;

import org.litespring.bean.BeanDefinition;
import org.litespring.bean.factory.BeanCreateException;
import org.litespring.bean.factory.BeanFactory;
import org.litespring.bean.factory.config.ConfigerableBeanFactory;
import org.litespring.utils.ClassUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缺省的BeanFactory
 * @author : Lin Can
 * @date : 2018/12/10 20:59
 */
public class DefaultBeanFactory extends DefaultSingletonRegister implements BeanDefinitionRegistry, ConfigerableBeanFactory {

    /**
     * 存放id和对应的bean定义
     */
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

    private ClassLoader classLoader;

    /**
     * 根据beanId获取定义
     * @param beanId beanId
     * @return id 对应的bean 定义
     */
    public BeanDefinition getBeanDefinition(String beanId) {
        return beanDefinitionMap.get(beanId);
    }

    public Object getBean(String beanId) {

        BeanDefinition definition = getBeanDefinition(beanId);
        if (definition == null) {
            throw new BeanCreateException("创建bean失败：beanDefinition is null");
        }

        if (definition.isSingleton()) {
            Object bean = getSingleton(beanId);
            if (bean == null) {
                bean = createBean(definition);
                registerSingleton(beanId,bean);
            }
            return bean;
        }
        return createBean (definition);
    }

    private Object createBean(BeanDefinition beanDefinition) {
        ClassLoader classLoader = getClassLoader();
        String beanClassName = beanDefinition.getBeanClassName();
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

    public ClassLoader getClassLoader() {
        return this.classLoader==null ? ClassUtils.getDefaultClassLoader() : this.classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }
}
