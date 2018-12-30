package org.litespring.bean.factory;

import org.litespring.bean.BeanException;

/**
 * @author : Lin Can
 * @date : 2018/12/30 9:49
 */
public class BeanDefinitionStoreException extends BeanException {
    public BeanDefinitionStoreException(String msg) {
        super(msg);
    }

    public BeanDefinitionStoreException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
