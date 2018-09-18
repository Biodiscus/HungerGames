package com.capgemini.hungergames.model.item;

public class Item {
    public static final float EMPTY_MODIFIER = 0.0f;

    private float attackModifier;
    private float defenseModifier;

    public Item(float attackModifier, float defenseModifier) {
        this.attackModifier = attackModifier;
        this.defenseModifier = defenseModifier;
    }

    public float getAttackModifier() {
        return attackModifier;
    }

    public void setAttackModifier(float attackModifier) {
        this.attackModifier = attackModifier;
    }

    public float getDefenseModifier() {
        return defenseModifier;
    }

    public void setDefenseModifier(float defenseModifier) {
        this.defenseModifier = defenseModifier;
    }

    @Override
    public String toString() {
        return "Item{" +
                "attack=" + attackModifier +
                ", defense=" + defenseModifier +
                '}';
    }
}
