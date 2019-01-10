package org.litespring.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Lin Can
 * @date : 2019/1/10 21:16
 */
public class ConstructorArgument {

    private List<ValueHolder> valueHolderList = new ArrayList<ValueHolder>();

    public List<ValueHolder> getValueHolderList() {
        return this.valueHolderList;
    }

    public static class ValueHolder {

        private Object value;

        public ValueHolder(Object value) {
            this.value = value;
        }

        public Object getValue() {
            return this.value;
        }
    }
}
