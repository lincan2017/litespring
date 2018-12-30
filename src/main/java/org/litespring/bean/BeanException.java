package org.litespring.bean;

/**
 * @author : Lin Can
 * @date : 2018/12/22 14:13
 */
public class BeanException extends RuntimeException {

    public BeanException(String msg) {
        super(msg);
    }

    public BeanException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
