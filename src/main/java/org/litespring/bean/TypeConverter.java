package org.litespring.bean;

/**
 * @author : Lin Can
 * @date : 2019/1/6 23:09
 */
public interface TypeConverter {
    <T> T convertIfNecessary(Object value, Class<T> clazz);
}
