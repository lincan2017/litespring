package org.litespring.bean.factory.support;

import org.litespring.bean.factory.config.RuntimeBeanReference;
import org.litespring.bean.factory.config.TypedStringValue;

/**
 * bean定义转换类
 *
 * @author : Lin Can
 * @date : 2019/1/4 23:32
 */
public class BeanDefinitionValueResolver {
    private final DefaultBeanFactory factory;

    public BeanDefinitionValueResolver(DefaultBeanFactory factory) {
        this.factory = factory;
    }


    public Object resolveValueIfNecessary(Object value) {
        if (value instanceof RuntimeBeanReference) {
            RuntimeBeanReference reference = (RuntimeBeanReference) value;
            return this.factory.getBean(reference.getName());
        } else if (value instanceof TypedStringValue) {
            TypedStringValue stringValue = (TypedStringValue) value;
            return stringValue.getValue();
        } else {
            throw new RuntimeException("the value " + value + " has not implemented");
        }
    }
}
