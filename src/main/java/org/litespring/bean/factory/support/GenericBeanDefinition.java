package org.litespring.bean.factory.support;

import org.litespring.bean.BeanDefinition;

/**
 * 通用的BeanDefinition的实现
 * @author : Lin Can
 * @date : 2018/12/12 21:20
 */
public class GenericBeanDefinition implements BeanDefinition {
    /**
     * beadId
     */
    private String id;

    /**
     * classpath
     */
    private String classFullName;

    public GenericBeanDefinition(String id, String clazzFullName) {
        this.id = id;
        this.classFullName = clazzFullName;
    }

    public String getBeanClassName() {
        return this.classFullName;
    }
}
