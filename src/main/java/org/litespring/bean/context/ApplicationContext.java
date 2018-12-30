package org.litespring.bean.context;

/**
 * @author : Lin Can
 * @date : 2018/12/30 22:59
 */
public interface ApplicationContext {

    /**
     * 根据beanId获取bean实例
     * @param id beanId
     * @return 该beanId对应的bean实例
     */
    Object getBean(String id);
}
