package org.litespring.property.editor;

import org.litespring.utils.StringUtils;

import java.beans.PropertyEditorSupport;

/**
 * @author : Lin Can
 * @date : 2019/1/5 23:34
 */
public class CustomBooleanEditor extends PropertyEditorSupport {

    private final boolean allowEmpty;

    private final String VALUE_TRUE = "true";

    private final String VALUE_FALSE = "false";

    private final String VALUE_YES = "yes";

    private final String VALUE_NO = "no";

    private final String VALUE_1 = "1";

    private final String VALUE_0 = "0";

    private final String VALUE_ON = "on";

    private final String VALUE_OFF = "off";

    public CustomBooleanEditor(boolean allowEmpty) {
        this.allowEmpty = allowEmpty;
    }

    @Override
    public void setAsText(String text) {
        String input = text != null ? text.trim() : null;

        if (input == null || !StringUtils.hasText(input)) {
            setValue(null);
        } else if (VALUE_1.equalsIgnoreCase(input) || VALUE_ON.equalsIgnoreCase(input) ||
                VALUE_TRUE.equalsIgnoreCase(input) || VALUE_YES.equalsIgnoreCase(input)) {
            setValue(Boolean.TRUE);
        } else if (VALUE_0.equalsIgnoreCase(input) || VALUE_OFF.equalsIgnoreCase(input) ||
                VALUE_FALSE.equalsIgnoreCase(input) || VALUE_NO.equalsIgnoreCase(input)) {
            setValue(Boolean.FALSE);
        } else {
            throw new IllegalArgumentException("Invalid boolean value [" + text + "]");
        }
    }

}
