package org.litespring.bean.factory.support;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.bean.factory.BeanDefinition;
import org.litespring.bean.factory.BeanFactory;
import org.litespring.utils.ClassUtils;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : Lin Can
 * @date : 2018/12/10 20:59
 */
public class DefaultBeanFactory implements BeanFactory {

    private final static String ID_ATTRIBUTE = "id";

    private final static String CLASS_ATTRIBUTE = "class";

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();


    public DefaultBeanFactory(String configPath) {
        loadBeanDefinition(configPath);
    }

    private void loadBeanDefinition(String configPath) {
        InputStream inputStream = null;

        try {
            ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
            inputStream = classLoader.getResourceAsStream(configPath);
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(inputStream);
            Element rootEL = document.getRootElement();
            if (!"beans".equalsIgnoreCase(rootEL.getName())) {
                //todo  根节点不正确的处理
                return;
            }
            Iterator<Element> elementIterator = rootEL.elementIterator();
            while (elementIterator.hasNext()) {
                Element element = elementIterator.next();
                String id = element.attributeValue(ID_ATTRIBUTE);
                String clazzFullName = element.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition beanDefinition = new GenericBeanDefinition(id, clazzFullName);
                this.beanDefinitionMap.put(id, beanDefinition);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {

                }
            }
        }
    }


    public BeanDefinition getDefinition(String beanId) {
        return beanDefinitionMap.get(beanId);
    }

    public Object getBean(String beanId) {
        ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
        BeanDefinition definition = getDefinition(beanId);
        if (definition == null) {
            throw new RuntimeException("没有对应的bean定义");
        }
        String beanClassName = definition.getBeanClassName();
        try {
            Class beanClass = classLoader.loadClass(beanClassName);
            return beanClass.newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}
