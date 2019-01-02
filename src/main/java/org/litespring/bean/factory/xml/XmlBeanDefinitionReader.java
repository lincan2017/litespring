package org.litespring.bean.factory.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.bean.BeanDefinition;
import org.litespring.bean.factory.BeanDefinitionStoreException;
import org.litespring.bean.factory.support.BeanDefinitionRegistry;
import org.litespring.bean.factory.support.GenericBeanDefinition;
import org.litespring.core.io.Resource;
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

    private final static String SCOPE_ATTRIBUTE = "scope";

    private BeanDefinitionRegistry beanDefinitionRegistry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    public void loadBeanDefinition(Resource resource) {
        InputStream inputStream = null;

        try {
            inputStream = resource.getInputStream();
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(inputStream);
            Element rootEL = document.getRootElement();
            if (!"beans".equalsIgnoreCase(rootEL.getName())) {
                throw new BeanDefinitionStoreException("解析bean定义出错：xml的rootElement不是beans");
            }
            Iterator elementIterator = rootEL.elementIterator();
            while (elementIterator.hasNext()) {
                Element element = (Element) elementIterator.next();
                String id = element.attributeValue(ID_ATTRIBUTE);
                String clazzFullName = element.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition beanDefinition = new GenericBeanDefinition(id, clazzFullName);
                if (element.attribute(SCOPE_ATTRIBUTE) != null) {
                    beanDefinition.setScope(element.attributeValue(SCOPE_ATTRIBUTE));
                }
                this.beanDefinitionRegistry.registerBeanDefinition(id, beanDefinition);
            }
        } catch (Exception e) {
            throw new BeanDefinitionStoreException("解析bean定义出错", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    //do noting
                }
            }
        }
    }
}
