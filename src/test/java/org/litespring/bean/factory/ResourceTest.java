package org.litespring.bean.factory;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.bean.core.io.ClassPathResource;
import org.litespring.bean.core.io.FileSystemResource;
import org.litespring.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

/**
 * 测试用Resource加载资源
 *
 * @author : Lin Can
 * @date : 2018/12/30 10:21
 */
public class ResourceTest {

    @Test
    public void testClassPathResource()  throws IOException{
        Resource resource = new ClassPathResource("petStore.xml");
        InputStream inputStream = null;
        try {
            inputStream = resource.getInputStream();
            // 不充分测试 简单判断inputStream不为空
            Assert.assertNotNull(inputStream);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    // do nothing
                }
            }
        }
    }

    @Test
    public void testFileSystemResource () throws IOException {

        // hardCode!!!!
        Resource r = new FileSystemResource("E:\\selfProject\\litespring\\files\\petstore.xml");
        InputStream inputStream = null;
        try {
            inputStream = r.getInputStream();
            // 注意：这个测试其实并不充分！！
            Assert.assertNotNull(inputStream);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    // do nothing
                }
            }
        }
    }
}
