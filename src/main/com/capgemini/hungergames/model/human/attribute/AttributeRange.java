package main.com.capgemini.hungergames.model.human.attribute;

import java.util.Random;

public class AttributeRange {
    private float min;
    private float max;

    public AttributeRange(float min, float max) {
        this.min = min;
        this.max = max;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float generate(Random random) {
        float diff = max - min;
        return min + (random.nextFloat() * diff);
    }

    @Override
    public String toString() {
        return "AttributeRange{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }
}
