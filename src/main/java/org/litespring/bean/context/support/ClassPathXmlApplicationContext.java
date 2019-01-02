package org.litespring.bean.context.support;

import org.litespring.bean.core.io.ClassPathResource;
import org.litespring.core.io.Resource;

/**
 * @author : Lin Can
 * @date : 2018/12/30 22:59
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    public ClassPathXmlApplicationContext(String path) {
        super(path);
    }

    @Override
    public Resource getResourceByPath(String path) {
        return new ClassPathResource(path,this.getClassLoader());
    }
}
