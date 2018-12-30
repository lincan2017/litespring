package org.litespring.bean.factory;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.bean.PetStore;
import org.litespring.bean.context.ApplicationContext;
import org.litespring.bean.context.ClassPathXmlApplicationCotext;
import org.litespring.bean.context.FilePathXmlApplicationContext;

/**
 * 测试用applicationContext管理bean 隐藏beanBeanFactory
 *
 * @author : Lin Can
 * @date : 2018/12/30 10:19
 */
public class ApplicationContextTest {

    @Test
    public void testGetBean() {
        ApplicationContext ctx = new ClassPathXmlApplicationCotext("petStore.xml");
        PetStore petStore = (PetStore) ctx.getBean("petStore");
        Assert.assertNotNull(petStore);
    }

    @Test
    public void testGetBeanByFilePathXmlApplicationContext() {
        ApplicationContext ctx = new FilePathXmlApplicationContext("E:\\selfProject\\litespring\\files\\petstore.xml");
        PetStore petStore = (PetStore) ctx.getBean("petStore");
        Assert.assertNotNull(petStore);
    }
}
