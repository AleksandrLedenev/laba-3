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
        List<Personage> SelectedEnemies = new ArrayList<>();
        for (Personage enemy:enemies) {
            double res = pow(enemy.getX() - getX(), 2) + pow(enemy.getY() - getY(), 2);
            if (sqrt(res) < 2){
                SelectedEnemies.add(enemy);
            }
        }
        return SelectedEnemies;
        }
}
