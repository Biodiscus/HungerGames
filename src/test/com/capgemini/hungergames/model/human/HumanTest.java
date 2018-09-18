package com.capgemini.hungergames.model.human;

import com.capgemini.hungergames.model.Group;
import com.capgemini.hungergames.model.human.Human;
import com.capgemini.hungergames.model.human.Man;
import com.capgemini.hungergames.model.human.attribute.Attribute;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanTest {
    private final float EPSILON = 0.00000001F;
    private Human human;

    @BeforeEach
    public void init() {
        Attribute attribute = new Attribute();
        attribute.setAttack(10F);
        attribute.setDefense(20F);
        attribute.setHealth(100F);
        attribute.setChance(0.5F);

        human = new Man("Test", attribute);
    }

    @Test
    public void getHumanNameWithGroup() {
        human.setGroup(Group.DISTRICT);
        String name = human.getName();
        assertEquals("Test of group District", name);

        human.setGroup(Group.CAREERS);
        name = human.getName();
        assertEquals("Test of group Careers", name);
    }

    @Test
    public void getHumanWithoutGroup() {
        String name = human.getName();
        assertEquals("Test", name);

        human.setGroup(Group.NONE);
        name = human.getName();
        assertEquals("Test", name);
    }

    @Test
    public void hurt() {
        Human human2 = new Human("Test2", 0F, 20F, 100F, 0F);
        human2.hurt(30F);
        assertEquals(90F, human2.getHealth(), EPSILON);

        human2 = new Human("Test2", 0F, 0F, 100F, 0F);
        human2.hurt(0F);
        assertEquals(100F, human2.getHealth(), EPSILON);

        human2 = new Human("Test2", 0F, 10F, 100F, 0F);
        human2.hurt(5F);
        assertEquals(100F, human2.getHealth(), EPSILON);
        // TODO: Item modifiers
    }

    @Test
    public void attack() {
        Human human = new Human("Test2", 10F, 0F, 100F, 0F);

        human.attack(this.human);
    }
}
