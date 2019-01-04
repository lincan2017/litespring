package org.litespring.bean.factory.config;

/**
 * @author : Lin Can
 * @date : 2019/1/3 23:51
 */
public class RuntimeBeanReference {

    private String name;

    public RuntimeBeanReference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
