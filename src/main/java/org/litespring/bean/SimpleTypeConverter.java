package org.litespring.bean;

import org.litespring.property.editor.CustomBooleanEditor;
import org.litespring.property.editor.CustomNumberEditor;
import org.litespring.utils.ClassUtils;

import java.beans.PropertyEditor;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : Lin Can
 * @date : 2019/1/6 23:11
 */
public class SimpleTypeConverter implements TypeConverter {

    private Map<Class<?>, PropertyEditor> editorMap = new HashMap<Class<?>, PropertyEditor>();

    public <T> T convertIfNecessary(Object value, Class<T> clazz) {
        if (ClassUtils.isAssignableValue(clazz,value)) {
            return (T) value;
        } else {
            if (value instanceof String) {
                PropertyEditor editor = findEditor(clazz);
                try {
                    editor.setAsText((String) value);
                } catch (IllegalArgumentException e) {
                    throw new TypeMismatchException(value,clazz);
                }
                return (T)editor.getValue();
            } else {
                throw new RuntimeException("Todo : can't convert value for "+value +" class:"+clazz);
            }
        }
    }

    private PropertyEditor findEditor (Class clazz) {
        PropertyEditor editor = this.getEditor (clazz);
        if(editor == null){
            throw new RuntimeException("Editor for "+ clazz +" has not been implemented");
        }
        return editor;
    }

    private PropertyEditor getEditor(Class clazz) {
        if (editorMap.size() <= 0) {
            createEditorMap();
        }
        return editorMap.get(clazz);
    }

    private void createEditorMap() {
        editorMap.put(boolean.class,new CustomBooleanEditor(false));
        editorMap.put(Boolean.class,new CustomBooleanEditor(true));

        editorMap.put(Integer.class,new CustomNumberEditor(Integer.class,true));
        editorMap.put(int.class,new CustomNumberEditor(Integer.class,false));
    }


}
