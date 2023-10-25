package ru.amm.ledenev;

import java.util.*;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Swordsman extends Personage {
    public Swordsman(String name, int level, int x, int y) {
        super(name, level, x, y);
    }


    @Override
    public List<Personage> chooseEnemiesToFight(List<Personage> enemies){
        List<Personage> selectedEnemies = new ArrayList<>();
        for (Personage enemy:enemies) {
            if (rangeTo(enemy) < 2){
                selectedEnemies.add(enemy);
            }
        }
        return selectedEnemies;
        }
}
