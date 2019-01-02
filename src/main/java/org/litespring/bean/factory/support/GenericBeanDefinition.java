package org.litespring.bean.factory.support;

import org.litespring.bean.BeanDefinition;

/**
 * 通用的BeanDefinition的实现
 * @author : Lin Can
 * @date : 2018/12/12 21:20
 */
public class GenericBeanDefinition implements BeanDefinition {

    private Boolean isSingleton = true;

    private Boolean isPrototype = false;

    private String scope = SCOPE_DEFAULT;
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

    public String getScope() {
        return scope;
    }

    public Boolean isSingleton() {
        return isSingleton;
    }

    public boolean isPrototype() {
        return isPrototype;
    }

    public void setScope(String scope) {
        this.scope = scope;
        isSingleton = SCOPE_SINGLETON.equalsIgnoreCase(scope) || SCOPE_DEFAULT.equals(scope);
        isPrototype = SCOPE_PROTOTYPE.equalsIgnoreCase(scope);
    }
}
