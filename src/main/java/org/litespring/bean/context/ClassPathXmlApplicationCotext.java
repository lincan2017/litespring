package org.litespring.bean.context;

import org.litespring.bean.core.io.ClassPathResource;
import org.litespring.bean.factory.support.DefaultBeanFactory;
import org.litespring.bean.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.Resource;

/**
 * @author : Lin Can
 * @date : 2018/12/30 22:59
 */
public class ClassPathXmlApplicationCotext implements ApplicationContext {

    private DefaultBeanFactory defaultBeanFactory;

    public ClassPathXmlApplicationCotext(String xmlPath) {
        defaultBeanFactory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(defaultBeanFactory);
        Resource resource = new ClassPathResource(xmlPath);
        reader.loadBeanDefinition(resource);
    }

    /**
     * 根据id获取对应的实例
     * @param id beanId
     * @return 返回该id对应的bean实例
     */
    public Object getBean(String id) {
        return defaultBeanFactory.getBean(id);
    }
}
