package com.capgemini.hungergames.model.human;

import com.capgemini.hungergames.model.human.attribute.Attribute;

public class Human extends Attribute {
    private boolean alive;

    public Human(Attribute attribute) {
        this(attribute.getAttack(), attribute.getDefense(),
                attribute.getHealth(), attribute.getChance());
    }

    public Human(float attack, float defense, float health, float chance) {
        super(attack, defense, health, chance);
        alive = true;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void attack(Human human) {
        float attackAmount = getAttack();

        // Loop trough all the items, do the calculations for the modifiers

        human.hurt(attackAmount);
    }

    public void hurt(float amount) {
        float hp = getHealth();

        // Simple calculation for now
        float diff = amount - getDefense();

        // Don't want to give the player HP
        if (diff > 0) {
            setHealth(hp - diff);
            System.out.println("Got hurt for: " + diff+", new hp:"+getHealth());
        }
    }

    @Override
    public void setHealth(float health) {
        super.setHealth(health);

        if(this.getHealth() <= 0) {
            this.alive = false;
        }
    }

    // public void attack();
    // public void getAttacked(int amount);
    // public void regen();
    /// Etc....


    @Override
    public String toString() {
        return "Human{" +
                "alive=" + alive +
                ", " + super.toString() +
                '}';
    }
}