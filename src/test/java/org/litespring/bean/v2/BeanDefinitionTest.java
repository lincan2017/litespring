package org.litespring.bean.v2;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.bean.BeanDefinition;
import org.litespring.bean.PropertyValue;
import org.litespring.core.io.ClassPathResource;
import org.litespring.bean.factory.support.DefaultBeanFactory;
import org.litespring.bean.factory.config.RuntimeBeanReference;
import org.litespring.bean.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.Resource;

import java.util.List;

/**
 * @author : Lin Can
 * @date : 2019/1/3 23:25
 */
public class BeanDefinitionTest {

    @Test
    public void testGetBeanDefinition () {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = new ClassPathResource("pet_store_service.xml");
        reader.loadBeanDefinition(resource);
        BeanDefinition beanDefinition = factory.getBeanDefinition("petStoreService");
        List<PropertyValue> propertyValueList = beanDefinition.getPropertyValueList();

        //根据配置文件可判断是3
        Assert.assertEquals(propertyValueList.size(),4);

        {
            PropertyValue pv = this.getProperValue ("itemDao",propertyValueList);

            Assert.assertNotNull(pv);

            Assert.assertTrue(pv.getValue() instanceof RuntimeBeanReference);
        }


        {
            PropertyValue pv = this.getProperValue ("accountDao",propertyValueList);

            Assert.assertNotNull(pv);

            Assert.assertTrue(pv.getValue() instanceof RuntimeBeanReference);
        }
    }

    private PropertyValue getProperValue(String name, List<PropertyValue> propertyValueList) {
        for (PropertyValue propertyValue : propertyValueList) {
            if (propertyValue.getName().equals(name)) {
                return propertyValue;
            }
        }
        throw new IllegalArgumentException("没有name为"+name+"对应的property属性");
    }
}
