package me.codalot.jump.objects;

import lombok.Getter;

@Getter
@SuppressWarnings("unused")
public class Modifier {

    private ModifierType type;
    private double value;

    public Modifier(String serialized) {
        ModifierType type = ModifierType.get(serialized.substring(0, 1));
        if (type == null) {
            this.type = ModifierType.SET;
            value = Double.parseDouble(serialized);
        } else {
            this.type = type;
            value = Double.parseDouble(serialized.substring(1));
        }
    }

    public Modifier(ModifierType type, double value) {
        this.type = type;
        this.value = value;
    }

    public double modify(double currentValue) {
        return type.apply(currentValue, value);
    }

}
