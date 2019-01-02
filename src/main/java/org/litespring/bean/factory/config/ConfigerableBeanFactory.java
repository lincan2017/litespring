package org.litespring.bean.factory.config;

import org.litespring.bean.factory.BeanFactory;

/**
 * 用于配置classloader
 *
 * @author : Lin Can
 * @date : 2019/1/1 21:55
 */
public interface ConfigerableBeanFactory extends BeanFactory {

    ClassLoader getClassLoader();

    void setClassLoader(ClassLoader classLoader);
}
