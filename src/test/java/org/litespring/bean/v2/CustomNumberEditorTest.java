package org.litespring.bean.v2;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.property.editor.CustomNumberEditor;

/**
 * @author : Lin Can
 * @date : 2019/1/5 22:22
 */
public class CustomNumberEditorTest {

    @Test
    public void testGetNumber () {
        CustomNumberEditor numberEditor = new CustomNumberEditor(Integer.class,true);
        numberEditor.setAsText("3");
        Object value = numberEditor.getValue();

        Assert.assertNotNull(value);
        Assert.assertTrue(value instanceof Integer);
        Assert.assertEquals(((Integer)value).intValue(),3);

        numberEditor.setAsText("");
        Assert.assertNull(numberEditor.getValue());

        try {
            numberEditor.setAsText("3.1");
            Assert.fail();
        } catch (IllegalArgumentException e) {

        }

    }
}
