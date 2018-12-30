package org.litespring.bean.factory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.bean.BeanDefinition;
import org.litespring.bean.PetStore;
import org.litespring.bean.core.io.ClassPathResource;
import org.litespring.bean.factory.support.DefaultBeanFactory;
import org.litespring.bean.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.Resource;

/**
 * 测试BeanFactory
 *
 * @author : Lin Can
 * @date : 2018/12/10 20:56
 */
public class BeanFactoryTest {

    private DefaultBeanFactory factory;
    private XmlBeanDefinitionReader reader;

    @Before
    public void setUp () {
        //缺省的BeanFactory用于获取bean
        factory = new DefaultBeanFactory();

        //用于解析xml的Reader
        reader = new XmlBeanDefinitionReader(factory);

    }

    /**
     * 获取Bean定义
     *
     * @author : Lin Can
     * @date: 2018/12/20 13:17
     */
    @Test
    public void testGetDefinition() {

        Resource resource = new ClassPathResource("petstore.xml");
        //加载配置文件
        reader.loadBeanDefinition(resource);

        //根据id获取bean定义
        BeanDefinition beanDefinition = factory.getBeanDefinition("petStore");

        Assert.assertEquals("org.litespring.bean.PetStore", beanDefinition.getBeanClassName());

        //获取特定id对应的bean实例
        PetStore petStore = (PetStore) factory.getBean("petStore");

        Assert.assertNotNull(petStore);
    }

    @Test
    public void testInvalidBean() {

        Resource resource = new ClassPathResource("petstore.xml");
        //加载配置文件
        reader.loadBeanDefinition(resource);

        //获取特定id对应的bean实例
        try {
            factory.getBean("invalidBean");
        } catch (BeanCreateException e) {
            return;
        }
        Assert.fail("except BeanCreateException");
    }

    @Test
    public void testInvalidXML() {

        try {
            //加载配置文件
            Resource resource = new ClassPathResource("XXX.xml");
            reader.loadBeanDefinition(resource);
        } catch (BeanDefinitionStoreException e) {
            return;
        }
        Assert.fail("except BeanDefinitionStoreException");


    }
}
