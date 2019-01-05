package org.litespring.bean.v2;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.property.editor.CustomBooleanEditor;

/**
 * @author : Lin Can
 * @date : 2019/1/5 23:30
 */
public class CustomBooleanEditorTest {
    @Test
    public void testGetBoolean() {
        CustomBooleanEditor editor = new CustomBooleanEditor(true);
        editor.setAsText("yes");
        Object value = editor.getValue();

        Assert.assertTrue(value instanceof Boolean);
        Assert.assertEquals(value,true);

        editor.setAsText("true");
        value = editor.getValue();

        Assert.assertTrue(value instanceof Boolean);
        Assert.assertEquals(value,true);

        editor.setAsText("1");
        value = editor.getValue();

        Assert.assertTrue(value instanceof Boolean);
        Assert.assertEquals(value,true);

        editor.setAsText("on");
        value = editor.getValue();

        Assert.assertTrue(value instanceof Boolean);
        Assert.assertEquals(value,true);

        editor.setAsText("false");
        value = editor.getValue();

        Assert.assertTrue(value instanceof Boolean);
        Assert.assertEquals(value,false);

        editor.setAsText("off");
        value = editor.getValue();

        Assert.assertTrue(value instanceof Boolean);
        Assert.assertEquals(value,false);

        editor.setAsText("0");
        value = editor.getValue();

        Assert.assertTrue(value instanceof Boolean);
        Assert.assertEquals(value,false);

        editor.setAsText("no");
        value = editor.getValue();

        Assert.assertTrue(value instanceof Boolean);
        Assert.assertEquals(value,false);
    }
}
