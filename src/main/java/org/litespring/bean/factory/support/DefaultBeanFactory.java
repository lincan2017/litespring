package org.litespring.bean.factory.support;

import org.litespring.bean.BeanDefinition;
import org.litespring.bean.PropertyValue;
import org.litespring.bean.SimpleTypeConverter;
import org.litespring.bean.TypeConverter;
import org.litespring.bean.factory.BeanCreateException;
import org.litespring.bean.factory.config.ConfigerableBeanFactory;
import org.litespring.utils.ClassUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缺省的BeanFactory
 *
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
     *
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
                registerSingleton(beanId, bean);
            }
            return bean;
        }
        return createBean(definition);
    }


    /**
     * 根据bean定义和bean为bean的属性设值
     *
     * @param definition  bean定义
     * @param bean 由bean定义中获取的bean
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void populateBean(BeanDefinition definition, Object bean) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        //获取property属性
        List<PropertyValue> propertyValueList = definition.getPropertyValueList();
        if (propertyValueList == null || propertyValueList.isEmpty()) {
            return;
        }

        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(this);

        TypeConverter converter = new SimpleTypeConverter();

        for (PropertyValue propertyValue : propertyValueList) {
            String name = propertyValue.getName();
            Object originalVal = propertyValue.getValue();

            Object resolveVal = resolver.resolveValueIfNecessary(originalVal);

            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());

            //获取属性描述
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor descriptor : propertyDescriptors) {
                if (descriptor.getName().equals(name)) {
                    Object convertedValue = converter.convertIfNecessary(resolveVal,descriptor.getPropertyType());
                    descriptor.getWriteMethod().invoke(bean, convertedValue);
                    break;
                }
            }
        }
    }

    private Object createBean(BeanDefinition beanDefinition) {
        Object bean = initializeBean(beanDefinition);
        try {
            populateBean(beanDefinition, bean);
            return bean;
        } catch (Exception e) {
            throw new BeanCreateException("Failed to obtain BeanInfo for class [" + beanDefinition.getBeanClassName() + "]", e);
        }
    }

    private Object initializeBean(BeanDefinition beanDefinition) {
        ClassLoader classLoader = getClassLoader();
        String beanClassName = beanDefinition.getBeanClassName();
        try {
            Class beanClass = classLoader.loadClass(beanClassName);
            return beanClass.newInstance();
        } catch (Exception e) {
            throw new BeanCreateException("创建bean失败：", beanClassName, e);
        }
    }

    public void registerBeanDefinition(String id, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(id, beanDefinition);
    }

    public ClassLoader getClassLoader() {
        return this.classLoader == null ? ClassUtils.getDefaultClassLoader() : this.classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }
}
