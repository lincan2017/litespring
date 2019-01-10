package org.litespring.bean.v3;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.bean.BeanDefinition;
import org.litespring.bean.ConstructorArgument;
import org.litespring.bean.factory.config.RuntimeBeanReference;
import org.litespring.bean.factory.config.TypedStringValue;
import org.litespring.bean.factory.support.DefaultBeanFactory;
import org.litespring.bean.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.service.v3.PetStoreService;

import java.util.List;

/**
 * @author : Lin Can
 * @date : 2019/1/10 21:00
 */
public class BeanDefinitionTest {

    @Test
    public void testConstructorArgument() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);

        reader.loadBeanDefinition(new ClassPathResource("pet_store_service_v3.xml"));

        BeanDefinition definition = factory.getBeanDefinition("petStoreService");
        Assert.assertEquals("org.litespring.service.v3.PetStoreService", definition.getBeanClassName());

        ConstructorArgument constructorArgument = definition.getConstructorArgument();
        List<ConstructorArgument.ValueHolder> valueHolderList = constructorArgument.getValueHolderList();

        Assert.assertEquals(3,valueHolderList.size());

        RuntimeBeanReference accountDaoRef = (RuntimeBeanReference) valueHolderList.get(0).getValue();
        Assert.assertEquals("accountDao",accountDaoRef.getName());

        RuntimeBeanReference itemDaoRef = (RuntimeBeanReference) valueHolderList.get(1).getValue();
        Assert.assertEquals("itemDao",itemDaoRef.getName());

        TypedStringValue value = (TypedStringValue) valueHolderList.get(2).getValue();
        Assert.assertEquals("1",value.getValue());
    }
}
