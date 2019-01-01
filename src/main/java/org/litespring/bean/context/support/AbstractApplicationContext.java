package org.litespring.bean.context.support;

import org.litespring.bean.context.ApplicationContext;
import org.litespring.bean.factory.support.DefaultBeanFactory;
import org.litespring.bean.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.Resource;

/**
 * @author : Lin Can
 * @date : 2019/1/1 21:40
 */
public abstract class AbstractApplicationContext implements ApplicationContext {
    private DefaultBeanFactory defaultBeanFactory;

    protected AbstractApplicationContext(String xmlPath) {
        defaultBeanFactory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(defaultBeanFactory);
        Resource resource = getResourceByPath(xmlPath);
        reader.loadBeanDefinition(resource);
    }

    /**
     * 根据id获取对应的实例
     *
     * @param id beanId
     * @return 返回该id对应的bean实例
     */
    public Object getBean(String id) {
        return defaultBeanFactory.getBean(id);
    }

    /**
     * 根据路径获取资源
     * @param path 资源路径
     * @return 资源对象
     */
    public abstract Resource getResourceByPath(String path);
}
