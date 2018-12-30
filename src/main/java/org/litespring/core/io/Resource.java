package org.litespring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 资源管理接口
 * @author : Lin Can
 * @date : 2018/12/30 10:29
 */
public interface Resource {

    /**
     * 获取输入流
     * @return 返回配置文件对应的输入流
     */
    InputStream getInputStream() throws IOException;
}
