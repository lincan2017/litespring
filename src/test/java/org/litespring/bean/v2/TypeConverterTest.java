package org.litespring.bean.v2;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.bean.SimpleTypeConverter;
import org.litespring.bean.TypeConverter;
import org.litespring.bean.TypeMismatchException;

/**
 * @author : Lin Can
 * @date : 2019/1/5 23:50
 */
public class TypeConverterTest {
    @Test
    public void testConvertStringToInt() {
        TypeConverter converter = new SimpleTypeConverter();
        Integer i = converter.convertIfNecessary("3", Integer.class);
        Assert.assertEquals(3,i.intValue());

        try{
            converter.convertIfNecessary("3.1", Integer.class);
        }catch(TypeMismatchException e){
            return;
        }
        Assert.fail();
    }
    @Test
    public void testConvertStringToBoolean(){
        TypeConverter converter = new SimpleTypeConverter();
        Boolean b = converter.convertIfNecessary("true", Boolean.class);
        Assert.assertEquals(true,b);

        try{
            converter.convertIfNecessary("xxxyyyzzz", Boolean.class);
        }catch(TypeMismatchException e){
            return;
        }
        Assert.fail();
    }
}
