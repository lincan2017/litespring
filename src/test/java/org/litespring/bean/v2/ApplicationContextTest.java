package org.litespring.bean.v2;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.bean.context.ApplicationContext;
import org.litespring.bean.context.support.ClassPathXmlApplicationContext;
import org.litespring.service.PetStoreService;

/**
 * @author : Lin Can
 * @date : 2019/1/4 23:39
 */
public class ApplicationContextTest {
    @Test
    public void testGetBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("pet_store_service.xml");
        PetStoreService petStoreService = (PetStoreService) context.getBean("petStoreService");

        Assert.assertNotNull(petStoreService.getAccountDao());
        Assert.assertNotNull(petStoreService.getItemDao());

        Assert.assertEquals(petStoreService.getEnv(),"test");
    }
}
