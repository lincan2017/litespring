package org.litespring.bean;

/**
 * @author : Lin Can
 * @date : 2019/1/6 23:38
 */
public class TypeMismatchException extends BeanException {
    private transient Object value;

    private Class<?> requiredType;

    public TypeMismatchException( Object value, Class<?> requiredType) {
        super("Failed to convert value :"+value + "to type "+requiredType);
        this.value = value;
        this.requiredType = requiredType;
    }

    public Object getValue() {
        return value;
    }

    public Class<?> getRequiredType() {
        return requiredType;
    }
}
