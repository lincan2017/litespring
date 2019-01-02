package org.litespring.bean;

/**
 * bean定义类
 * @author : Lin Can
 * @date : 2018/12/10 21:39
 */
public interface BeanDefinition {

    /**
     * 单例
     */
    String SCOPE_SINGLETON = "singleton";

    /**
     * 原型
     */
    String SCOPE_PROTOTYPE = "prototype";

    /**
     * 默认scope
     */
    String SCOPE_DEFAULT = "";

    /**
     * 获取类名
     * @return 返回该定义中beanClassName
     */
    String getBeanClassName();

    /**
     * 获取该bean定义的实例化类型
     * @return 返回scope属性对应的值
     */
    String getScope();

    /**
     * 获取scope
     */
    void setScope(String scope);

    /**
     * 是否单例
     * @return Boolean
     */
    Boolean isSingleton();

    /**
     * 是否是原型
     * @return Boolean
     */
    boolean isPrototype();
}
