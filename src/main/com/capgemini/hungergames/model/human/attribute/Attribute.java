package com.capgemini.hungergames.model.human.attribute;

/**
 * Provides basic attributes that the human can use.
 * This is it's own class because both a humand and the generator will use it.
 */
public class Attribute {
    private float attack;
    private float defense;
    private float health;
    private float chance;

    public Attribute() {
    }

    public Attribute(float attack, float defense, float health, float chance) {
        this.attack = attack;
        this.defense = defense;
        this.health = health;
        this.chance = chance;
    }

    public float getAttack() {
        return attack;
    }

    public void setAttack(float attack) {
        this.attack = attack;
    }

    public float getDefense() {
        return defense;
    }

    public void setDefense(float defense) {
        this.defense = defense;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getChance() {
        return chance;
    }

    public void setChance(float chance) {
        this.chance = chance;
    }

    @Override
    public String toString() {
        return "Attribute {" +
                "attack=" + attack +
                ", defense=" + defense +
                ", health=" + health +
                ", chance=" + chance +
                '}';
    }
}
