package org.litespring.bean.factory;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.bean.BeanDefinition;
import org.litespring.bean.PetStore;
import org.litespring.bean.factory.support.DefaultBeanFactory;
import org.litespring.bean.factory.xml.XmlBeanDefinitionReader;

/**
 * @author : Lin Can
 * @date : 2018/12/10 20:56
 */
public class BeanFactoryTest {

    @Test
    public void testGetDefinition() {

        DefaultBeanFactory beanFactory = new DefaultBeanFactory();

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        xmlBeanDefinitionReader.loadBeanDefinition("petstore.xml");

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("petStore");

        Assert.assertEquals("org.litespring.bean.PetStore", beanDefinition.getBeanClassName());

        PetStore petStore = (PetStore) beanFactory.getBean("petStore");

        Assert.assertNotNull(petStore);
    }
}
