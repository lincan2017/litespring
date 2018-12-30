package org.litespring.bean.core.io;

import org.litespring.core.io.Resource;
import org.litespring.utils.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author : Lin Can
 * @date : 2018/12/30 10:40
 */
public class FileSystemResource implements Resource {

    /**
     * 配置文件路径
     */
    private String xmlPath;

    /**
     * 配置文件
     */
    private File file;

    public FileSystemResource(String xmlPath) {
        Assert.notNull(xmlPath,"Path must not be null");

        this.file = new File(xmlPath);
        this.xmlPath = xmlPath;

    }

    public InputStream getInputStream() throws FileNotFoundException {
        return new FileInputStream(file);
    }
}
