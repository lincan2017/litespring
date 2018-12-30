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
        this(xmlPath,null);
    }

    public ClassPathResource(String xmlPath, ClassLoader classLoader) {
        this.xmlPath = xmlPath;
        this.classLoader = ((classLoader==null) ? ClassUtils.getDefaultClassLoader() : classLoader);
    }

    public InputStream getInputStream() {
        return this.classLoader.getResourceAsStream(xmlPath);
    }

    public String getDiscription() {
        return xmlPath;
    }
}
