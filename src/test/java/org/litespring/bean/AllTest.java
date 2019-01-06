package org.litespring.bean;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.litespring.bean.factory.FactoryAllTest;
import org.litespring.bean.v2.V_2_AllTest;

/**
 * @author : Lin Can
 * @date : 2019/1/7 0:04
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        FactoryAllTest.class,
        V_2_AllTest.class
})
public class AllTest {
}
