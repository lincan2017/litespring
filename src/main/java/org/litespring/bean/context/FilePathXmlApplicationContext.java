package org.litespring.bean.context;

import org.litespring.bean.core.io.FileSystemResource;
import org.litespring.bean.factory.support.DefaultBeanFactory;
import org.litespring.bean.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.Resource;

/**
 * @author : Lin Can
 * @date : 2018/12/31 6:55
 */
public class FilePathXmlApplicationContext implements ApplicationContext {

    private DefaultBeanFactory defaultBeanFactory;

    public FilePathXmlApplicationContext(String xmlPath) {
        defaultBeanFactory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(defaultBeanFactory);
        Resource resource = new FileSystemResource(xmlPath);
        reader.loadBeanDefinition(resource);
    }

    /**
     * 根据id获取对应的实例
     * @param id  beanId
     * @return 该id对应的bean实例
     */
    public Object getBean(String id) {
        return defaultBeanFactory.getBean(id);
    }
}
