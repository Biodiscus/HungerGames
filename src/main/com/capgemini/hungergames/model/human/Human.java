package main.com.capgemini.hungergames.model.human;

import main.com.capgemini.hungergames.model.Group;
import main.com.capgemini.hungergames.model.human.attribute.Attribute;
import main.com.capgemini.hungergames.model.item.Item;
import main.com.capgemini.hungergames.util.FloatUtil;

import java.util.LinkedList;
import java.util.List;

public class Human extends Attribute {
    private boolean alive;
    private String name;
    private Group group;

    private float startHealth;
    private float startAttack;
    private float startDefense;

    private List<Item> items;

    public Human(String name, Attribute attribute) {
        this(name, attribute.getAttack(), attribute.getDefense(),
                attribute.getHealth(), attribute.getChance());
    }

    public Human(String name, float attack, float defense, float health, float chance) {
        super(attack, defense, health, chance);

        this.startHealth = health;
        this.startAttack = attack;
        this.startDefense = defense;

        this.alive = true;
        this.name = name;

        this.group = Group.NONE;

        this.items = new LinkedList<>();
    }

    // TODO: UNIT TEST
    public void attack(Human human) {
        if (!isAlive()) {
            return;
        }

        float attackAmount = getAttack();
        // Loop trough all the items, do the calculations for the modifiers
        for (Item item : items) {
            if (FloatUtil.isEqual(item.getAttackModifier(), 0F)) {
                attackAmount += startAttack * item.getAttackModifier();
            }
        }


        System.out.println("\t["+getName()+"] Attacks with: "+attackAmount);
        human.hurt(attackAmount);
    }

    // TODO: UNIT TEST
    public void hurt(float amount) {
        float hp = getHealth();

        // Simple calculation for now
        float defense = getDefense();

        // Loop trough all the items, do the calculations for the modifiers
        for (Item item : items) {
            if (FloatUtil.isEqual(item.getDefenseModifier(), 0F)) {
                defense += startDefense * item.getDefenseModifier();
            }
        }

        float diff = amount - defense;

        // Don't want to give the player HP
        if (diff > 0) {
            setHealth(hp - diff);
            System.out.println("\t["+getName()+"] Got hurt for: " + diff+", new HP:"+getHealth());
        }
    }

    // TODO: UNIT TEST
    public void heal() {
        setHealth(this.startHealth);
    }

    // TODO: UNIT TEST
    @Override
    public void setHealth(float health) {
        super.setHealth(health);

        if(this.getHealth() <= 0) {
            this.alive = false;
        }
    }

    // TODO: UNIT TEST
    public String getName() {
        if (getGroup() != Group.NONE) {
            return name + " of group "+getGroup();
        } else {
            return name;
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "HumanTest{" +
                "alive=" + alive +
                ", " + super.toString() +
                '}';
    }
}