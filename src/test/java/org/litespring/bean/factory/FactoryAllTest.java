package org.litespring.bean.factory;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * factory 下的所有的test
 * @author : Lin Can
 * @date : 2018/12/30 22:47
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        BeanFactoryTest.class,
        ResourceTest.class,
        ApplicationContextTest.class})
public class FactoryAllTest {

}
