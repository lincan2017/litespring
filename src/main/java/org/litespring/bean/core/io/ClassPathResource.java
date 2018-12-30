package org.litespring.bean.core.io;

import org.litespring.core.io.Resource;
import org.litespring.utils.ClassUtils;

import java.io.InputStream;

/**
 * @author : Lin Can
 * @date : 2018/12/30 10:31
 */
public class ClassPathResource implements Resource {
    private String xmlPath;
    private ClassLoader classLoader;

    public ClassPathResource(String xmlPath) {
        this.xmlPath = xmlPath;
        this.classLoader = ClassUtils.getDefaultClassLoader();
    }

    public InputStream getInputStream() {
        return this.classLoader.getResourceAsStream(xmlPath);
    }


}
