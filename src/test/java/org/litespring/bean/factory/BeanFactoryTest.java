package org.litespring.bean.factory;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.bean.PetStore;
import org.litespring.bean.factory.support.DefaultBeanFactory;

/**
 * @author : Lin Can
 * @date : 2018/12/10 20:56
 */
public class BeanFactoryTest {

    @Test
    public void testGetDefinition() {

        BeanFactory beanFactory = new DefaultBeanFactory("petStore.xml");

        BeanDefinition beanDefinition = beanFactory.getDefinition("petStore");

        Assert.assertEquals("org.litespring.bean.PetStore", beanDefinition.getBeanClassName());

        PetStore petStore = (PetStore) beanFactory.getBean("petStore");

        Assert.assertNotNull(petStore);
    }
}
