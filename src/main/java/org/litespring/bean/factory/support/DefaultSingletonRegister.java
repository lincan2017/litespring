package org.litespring.bean.factory.support;

import org.litespring.bean.factory.config.SingletonRegister;
import org.litespring.utils.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * singleton注册接口的默认实现
 * @author : Lin Can
 * @date : 2019/1/2 23:49
 */
public class DefaultSingletonRegister implements SingletonRegister {

    private Map<String,Object> singletonMap = new ConcurrentHashMap<String, Object>(64);

    public void registerSingleton(String beanId, Object singleObject) {
        Assert.notNull(beanId,"beanId must not be null");
        Object object = singletonMap.get(beanId);
        if (object != null) {
            throw new IllegalArgumentException(beanId + "已有对应的singleton, 不能重复注册");
        }
        singletonMap.put(beanId,singleObject);
    }

    public Object getSingleton(String beanId) {
        return singletonMap.get(beanId);
    }
}
