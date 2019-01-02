package org.litespring.bean.factory.config;

/**
 * singleton注册接口
 *
 * @author : Lin Can
 * @date : 2019/1/2 23:47
 */
public interface SingletonRegister {
    /**
     * 注册单例
     *
     * @param beanId       beanName
     * @param singleObject 该beanName对应的单例
     */
    void registerSingleton(String beanId, Object singleObject);

    /**
     * 根据beanId获取对应的单例
     *
     * @param beanId beanName
     * @return 对应的单例
     */
    Object getSingleton(String beanId);
}
