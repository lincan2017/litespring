package org.litespring.bean.factory.xml;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.bean.BeanDefinition;
import org.litespring.bean.ConstructorArgument;
import org.litespring.bean.PropertyValue;
import org.litespring.bean.factory.BeanDefinitionStoreException;
import org.litespring.bean.factory.config.RuntimeBeanReference;
import org.litespring.bean.factory.config.TypedStringValue;
import org.litespring.bean.factory.support.BeanDefinitionRegistry;
import org.litespring.bean.factory.support.GenericBeanDefinition;
import org.litespring.core.io.Resource;
import org.litespring.utils.StringUtils;

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

    private final static String NAME_ATTRIBUTE = "name";

    private final static String REF_ATTRIBUTE = "ref";

    private final static String VALUE_ATTRIBUTE = "value";

    private final static String PROPERTY_ELEMENT = "property";

    private final static String CONSTRUCTOR_ARG_ELEMENT = "constructor-arg";

    private final Log logger = LogFactory.getLog(getClass());

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
                parsePropertyElement(element, beanDefinition);
                parseConstructorArgElement(element, beanDefinition);
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

    private void parseConstructorArgElement(Element element, BeanDefinition bd) {
        Iterator<Element> elementIterator = element.elementIterator(CONSTRUCTOR_ARG_ELEMENT);

        while (elementIterator.hasNext()) {
            Element constructorArgEL = elementIterator.next();
            Object value = parsePropertyValue(constructorArgEL,bd,null);

            bd.getConstructorArgument().getValueHolderList().
                    add(new ConstructorArgument.ValueHolder(value));

        }
    }

    private void parsePropertyElement(Element element, BeanDefinition bd) {
        Iterator<Element> elementIterator = element.elementIterator(PROPERTY_ELEMENT);

        while (elementIterator.hasNext()) {
            Element propertyEL = elementIterator.next();

            String propertyName = propertyEL.attributeValue(NAME_ATTRIBUTE);
            if (!StringUtils.hasLength(propertyName)) {
                logger.fatal("Tag 'property' must have a 'name' attribute");
                return;
            }
            Object value = parsePropertyValue(propertyEL, bd, propertyName);

            PropertyValue propertyValue = new PropertyValue(propertyName, value);

            bd.getPropertyValueList().add(propertyValue);
        }
    }

    private Object parsePropertyValue(Element element, BeanDefinition bd, String propertyName) {
        String elementName = (propertyName != null) ?
                "<property> element for property '" + propertyName + "'" :
                "<constructor-arg> element";

        boolean hasRefAttr = (element.attribute(REF_ATTRIBUTE) != null);
        boolean hasValueAttr = (element.attribute(VALUE_ATTRIBUTE) != null);

        if (hasRefAttr) {
            return new RuntimeBeanReference(element.attributeValue(REF_ATTRIBUTE));
        } else if (hasValueAttr) {
            return new TypedStringValue(element.attributeValue(VALUE_ATTRIBUTE));
        } else {
            throw new RuntimeException(elementName + " must specify a ref or value");
        }
    }
}
