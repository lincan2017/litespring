package org.litespring.utils;

/**
 * 校验不能为空
 * @author : Lin Can
 * @date : 2018/12/30 10:46
 */
public class Assert {
    public static void notNull (Object obj, String msg) {
        if (obj == null) {
            throw new IllegalArgumentException(msg);
        }
    }
}
