package org.litespring.bean;

/**
 * @author : Lin Can
 * @date : 2019/1/3 23:40
 */
public class PropertyValue {

    private String name;

    private Object value;

    private boolean converted = false;

    private Object convertedValue;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public synchronized boolean isConverted() {
        return converted;
    }

    public void setConvertedValue(Object convertedValue) {
        this.converted = true;
        this.convertedValue = convertedValue;
    }

    public synchronized Object getConvertedValue() {
        return convertedValue;
    }
}
