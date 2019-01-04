package org.litespring.bean.v2;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.bean.factory.config.RuntimeBeanReference;
import org.litespring.bean.factory.config.TypedStringValue;
import org.litespring.bean.factory.support.BeanDefinitionValueResolver;
import org.litespring.bean.factory.support.DefaultBeanFactory;
import org.litespring.bean.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;
import org.litespring.dao.AccountDao;

/**
 * property属性值到真实类型的转换测试
 * @author : Lin Can
 * @date : 2019/1/4 23:24
 */
public class BeanDefinitionValueResolverTest {

    @Test
    public void testResolverRefValue () {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = new ClassPathResource("pet_store_service.xml");
        reader.loadBeanDefinition(resource);

        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(factory);
        RuntimeBeanReference reference = new RuntimeBeanReference("accountDao");
        Object bean = resolver.resolveValueIfNecessary(reference);

        Assert.assertNotNull(bean);
        Assert.assertTrue(bean instanceof AccountDao);

    }


    @Test
    public void testResolverTypedStringValue () {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = new ClassPathResource("pet_store_service.xml");
        reader.loadBeanDefinition(resource);

        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(factory);
        TypedStringValue value = new TypedStringValue("test");
        Object resolveValue = resolver.resolveValueIfNecessary(value);

        Assert.assertNotNull(resolveValue);
        Assert.assertEquals(resolveValue,"test");

    }
}
