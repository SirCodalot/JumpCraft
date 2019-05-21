package me.codalot.jump.objects;

import lombok.Getter;

@Getter
public enum ModifierType {

    SET("="),
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*", "x"),
    DIVIDE("/", ":");

    private String[] ids;

    ModifierType(String... ids) {
        this.ids = ids;
    }

    private boolean isId(String string) {
        for (String id : ids) {
            if (string.equalsIgnoreCase(id))
                return true;
        }
        return false;
    }

    public double apply(double c, double v) {
        switch (this) {
            case SET:
                return v;
            case ADD:
                return c + v;
            case SUBTRACT:
                return c - v;
            case MULTIPLY:
                return c * v;
            case DIVIDE:
                return c / v;
        }
        return c;
    }

    public static ModifierType get(String id) {
        for (ModifierType type : values()) {
            if (type.isId(id))
                return type;
        }
        return null;
    }
}
