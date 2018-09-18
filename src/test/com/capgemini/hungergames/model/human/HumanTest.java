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
}
