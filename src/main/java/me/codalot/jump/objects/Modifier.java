package me.codalot.jump.objects;

import lombok.Getter;

@Getter
@SuppressWarnings("unused")
public class Modifier {

    private ModifierType type;
    private double value;

    public Modifier(String serialized) {
        String[] split = serialized.split(":");

        if (split.length == 1) {
            type = ModifierType.SET;
            value = Double.parseDouble(split[0]);
        } else {
            type = ModifierType.get(split[0]);
            value = Double.parseDouble(split[1]);
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
