package me.codalot.jump.objects;

import lombok.Getter;

@Getter
public enum ModifierType {

    SET("S"),
    ADD("a"),
    SUBTRACT("s"),
    MULTIPLY("m"),
    DIVIDE("d");

    private String id;

    ModifierType(String id) {
        this.id = id;
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
            if (type.getId().equals(id))
                return type;
        }
        return SET;
    }
}
