package com.capgemini.hungergames;

import com.capgemini.hungergames.model.human.attribute.Attribute;
import com.capgemini.hungergames.model.human.Human;
import com.capgemini.hungergames.model.human.Man;
import com.capgemini.hungergames.model.human.Woman;
import com.capgemini.hungergames.model.human.attribute.AttributeRange;
import com.capgemini.hungergames.model.item.Item;
import com.capgemini.hungergames.model.item.Shield;
import com.capgemini.hungergames.model.item.Sword;
import com.capgemini.hungergames.util.NameUtil;

import javax.naming.Name;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Starting point for the application.
 *
 * @author Robin de Jong
 */
public class App {
    // The ranges used when creating new players
    private final AttributeRange ATTACK_RANGE   = new AttributeRange(50.0F, 100.0F);
    private final AttributeRange DEFENSE_RANGE  = new AttributeRange(5.0F, 10.0F);
    private final AttributeRange HEALTH_RANGE   = new AttributeRange(50.0F, 100.0F);
    private final AttributeRange CHANCE_RANGE   = new AttributeRange(0.0F, 1.0F);

    private final float CHANCE_TWO_PLAYERS_MEET = 0.5F; // 50% Two person meet
    private final float CHANCE_PLAYER_FINDS_ITEM = 0.2F; // 50% Two person meet

    // Amount of players that should play the game
    private final int AMOUNT = 23;
    // Range: Between 0 and 1, 0.5 = 50%
    private final float PERCENTAGE_MALE = 0.5f;
    private final float PERCENTAGE_FEMALE = 1 - PERCENTAGE_MALE;


    // TODO: This is ugly
    private final Item[] AVAILABLE_ITEMS = {new Sword(), new Shield()};

    public App() {
        List<Human> humanList = new LinkedList<>();

        AttributeGenerator generator = new AttributeGenerator(
                ATTACK_RANGE, DEFENSE_RANGE,
                HEALTH_RANGE, CHANCE_RANGE
        );

        try {
            humanList.addAll(createMale(AMOUNT, PERCENTAGE_MALE, generator));
            humanList.addAll(createFemale(AMOUNT, PERCENTAGE_FEMALE, generator));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        run(humanList);
    }

    private void run(List<Human> players) {
        int cycleCount = 0;
        List<Human> alive = players;

        while (alive.size() > 1) {
            cycleCount ++;
            simulateCycle(alive);

            alive = removeDeadPlayers(alive);
            System.out.println("Current cycle count: "+cycleCount+", players alive: "+alive.size());

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (alive.size() > 0) {
            System.out.println("The winner is: " + alive.get(0).getName());
        } else {
            System.out.println("Everyone died, no winner");
        }
    }

    private void simulateCycle(List<Human> alive) {
        letPlayersBattle(alive);
        letPlayersFindItems(alive);
        alive.forEach(Human::heal);
    }

    private void letPlayersFindItems(List<Human> alive) {
        alive.forEach((player) -> {
            boolean findsItem = Math.random() > CHANCE_PLAYER_FINDS_ITEM;
            if (findsItem) {
                int randomPos = (int) (AVAILABLE_ITEMS.length * Math.random());
                Item randomItem = AVAILABLE_ITEMS[randomPos];
                player.addItem(randomItem);
            }
        });
    }

    private void letPlayersBattle(List<Human> alive) {
        int len = alive.size();
        for (int i = 0; i < len - 1; i ++) {
            // TODO: Math.random() is good enough for now, I want to use Random.nextDouble() though
            double chance = Math.random();
            if (chance > CHANCE_TWO_PLAYERS_MEET) {
                playersFight(alive.get(i), alive.get(i + 1));
            }
        }
    }

    private void playersFight(Human first, Human second) {
        System.out.println("Player "+first.getName()+" (with items: "+first.getItems()+"), fights with player " + second.getName()+" (with items: "+second.getItems()+")");

        // TODO: Coin flip on who get's to attack first
        while(first.isAlive() && second.isAlive()) {
            first.attack(second);
            second.attack(first);

            System.out.println("First player alive: "+first.isAlive()+", second alive: "+second.isAlive());
        }
    }

    private List<Human> removeDeadPlayers(List<Human> players) {
        List<Human> alive = new ArrayList<>();

        players.forEach((ele) -> {
            if (ele.isAlive()) {
                alive.add(ele);
            }
        });

        return alive;
    }

    private List<Human> createMale(int amount, double percentageMale, AttributeGenerator generator) throws IOException, URISyntaxException {
        List<Human> humanList = new ArrayList<>();
        // Casting to an integer is the same as Math.floor, adding 0.5 makes it Math.ceil
        int count = (int) ((amount * percentageMale) + 0.5);

        for (int i = 0; i < count; i ++) {
            Attribute attribute = generator.generate();
            String name = NameUtil.generateName(NameUtil.Gender.MALE);
            humanList.add(new Man(name, attribute));
        }

        return humanList;
    }

    private List<Human> createFemale(int amount, double percentageFemale, AttributeGenerator generator) throws IOException, URISyntaxException {
        List<Human> humanList = new ArrayList<>();

        int count = (int) (amount * percentageFemale);

        for (int i = 0; i < count; i ++) {
            Attribute attribute = generator.generate();
            String name = NameUtil.generateName(NameUtil.Gender.FEMALE);
            humanList.add(new Woman(name, attribute));
        }

        return humanList;
    }

    public static void main(String[] args) {
        new App();
    }


    /**
     * An object that generates random attributes for the human.
     * Doesn't necessarily be its own class, but makes it easier for the Random generator.
     */
    class AttributeGenerator {
        private Random random;

        private AttributeRange attackRange;
        private AttributeRange defenseRange;
        private AttributeRange healthRange;
        private AttributeRange chanceRange;


        public AttributeGenerator() {
            random = new Random();
        }

        public AttributeGenerator(int seed) {
            random = new Random(seed);
        }

        public AttributeGenerator(AttributeRange attackRange, AttributeRange defenseRange, AttributeRange healthRange, AttributeRange chanceRange) {
            this();
            this.attackRange = attackRange;
            this.defenseRange = defenseRange;
            this.healthRange = healthRange;
            this.chanceRange = chanceRange;
        }

        public AttributeGenerator(AttributeRange attackRange, AttributeRange defenseRange, AttributeRange healthRange, AttributeRange chanceRange, int seed) {
            this(seed);
            this.attackRange = attackRange;
            this.defenseRange = defenseRange;
            this.healthRange = healthRange;
            this.chanceRange = chanceRange;
        }

        public Attribute generate() {
            Attribute attribute = new Attribute();
            attribute.setAttack(attackRange.generate(this.random));
            attribute.setDefense(defenseRange.generate(this.random));
            attribute.setHealth(healthRange.generate(this.random));
            attribute.setChance(chanceRange.generate(this.random));

            return attribute;
        }
    }
}
