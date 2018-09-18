package com.capgemini.hungergames.model.human;

import com.capgemini.hungergames.model.human.attribute.Attribute;

public class Human extends Attribute {
    public Human(Attribute attribute) {
        this(attribute.getAttack(), attribute.getDefense(),
                attribute.getHealth(), attribute.getChance());
    }

    public Human(float attack, float defense, float health, float chance) {
        super(attack, defense, health, chance);
    }

    // public void attack();
    // public void getAttacked(int amount);
    // public void regen();
    /// Etc....
}