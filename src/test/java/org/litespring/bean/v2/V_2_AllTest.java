package org.litespring.bean.v2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author : Lin Can
 * @date : 2019/1/5 16:58
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ApplicationContextTest.class,
        BeanDefinitionTest.class,
        BeanDefinitionValueResolverTest.class
})
public class V_2_AllTest {
}
