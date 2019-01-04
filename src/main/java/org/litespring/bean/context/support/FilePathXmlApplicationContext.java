package org.litespring.bean.context.support;

import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;

/**
 * @author : Lin Can
 * @date : 2018/12/31 6:55
 */
public class FilePathXmlApplicationContext extends AbstractApplicationContext {

    public FilePathXmlApplicationContext(String xmlPath) {
        super(xmlPath);
    }

    @Override
    public Resource getResourceByPath(String path) {
        return new FileSystemResource(path);
    }
}
