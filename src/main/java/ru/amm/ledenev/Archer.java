package ru.amm.ledenev;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Archer extends Personage {
    public Archer(String name, int level, int x, int y) {
        super(name, level, x, y);
    }

    @Override
    public List<Personage> chooseEnemiesToFight(List<Personage> enemies){
        List<Personage> SelectedEnemies = new ArrayList<>();
        for (Personage enemy:enemies) {
            double res = pow(enemy.getX() - getX(), 2) + pow(enemy.getY() - getY(), 2);
            if (sqrt(res) > 5 && sqrt(res) < 10){
                SelectedEnemies.add(enemy);
            }
        }//Как выбрать трех слабых (логика)
        return SelectedEnemies;
    }
}
