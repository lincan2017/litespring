package org.litespring.bean.factory.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.bean.BeanDefinition;
import org.litespring.bean.factory.support.BeanDefinitionRegistry;
import org.litespring.bean.factory.support.GenericBeanDefinition;
import org.litespring.utils.ClassUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * @author : Lin Can
 * @date : 2018/12/20 9:14
 */
public class XmlBeanDefinitionReader {

    private final static String ID_ATTRIBUTE = "id";

    private final static String CLASS_ATTRIBUTE = "class";

    BeanDefinitionRegistry beanDefinitionRegistry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    public void loadBeanDefinition(String configPath) {
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
                this.beanDefinitionRegistry.registerBeanDefinition(id,beanDefinition);
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
}
