package org.litespring.bean.factory;

import org.litespring.bean.BeanException;

/**
 * @author : Lin Can
 * @date : 2018/12/22 14:17
 */
public class BeanCreateException extends BeanException {

    private String beanName;

    public BeanCreateException(String msg) {
        super(msg);
    }

    public BeanCreateException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public BeanCreateException(String msg, String beanName) {
        super("Error creating bean with name '" + beanName + "': " + msg);
        this.beanName = beanName;
    }

    public BeanCreateException(String msg, String beanName,Throwable cause) {
        this(beanName, msg);
        initCause(cause);
    }

    public String getBeanName() {
        return beanName;
    }
}
