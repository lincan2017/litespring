package org.litespring.bean.factory;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.bean.BeanDefinition;
import org.litespring.bean.PetStore;
import org.litespring.bean.factory.support.DefaultBeanFactory;
import org.litespring.bean.factory.xml.XmlBeanDefinitionReader;

/**
 * 测试BeanFactory
 *
 * @author : Lin Can
 * @date : 2018/12/10 20:56
 */
public class BeanFactoryTest {

    /**
     * 获取Bean定义
     *
     * @author : Lin Can
     * @date: 2018/12/20 13:17
     */
    @Test
    public void testGetDefinition() {

        //缺省的BeanFactory用于获取bean
        DefaultBeanFactory beanFactory = new DefaultBeanFactory();

        //用于解析xml的Reader
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        //加载配置文件
        xmlBeanDefinitionReader.loadBeanDefinition("petstore.xml");

        //根据id获取bean定义
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("petStore");

        Assert.assertEquals("org.litespring.bean.PetStore", beanDefinition.getBeanClassName());

        //获取特定id对应的bean实例
        PetStore petStore = (PetStore) beanFactory.getBean("petStore");

        Assert.assertNotNull(petStore);
    }

    @Test
    public void testInvalidBean() {

        //缺省的BeanFactory用于获取bean
        DefaultBeanFactory beanFactory = new DefaultBeanFactory();
        //用于解析xml的Reader
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        //加载配置文件
        xmlBeanDefinitionReader.loadBeanDefinition("petstore.xml");

        //获取特定id对应的bean实例
        try {
            beanFactory.getBean("invalidBean");
        } catch (BeanCreateException e) {
            return;
        }
        Assert.fail("except BeanCreateException");
    }

    @Test
    public void testInvalidXML() {
        //缺省的BeanFactory用于获取bean
        DefaultBeanFactory beanFactory = new DefaultBeanFactory();
        //用于解析xml的Reader
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        //加载配置文件
        try {
            xmlBeanDefinitionReader.loadBeanDefinition("XXX.xml");
        } catch (BeanDefinitionStoreException e) {
            return;
        }
        Assert.fail("except BeanDefinitionStoreException");


    }
}
