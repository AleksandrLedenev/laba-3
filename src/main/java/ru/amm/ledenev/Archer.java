package ru.amm.ledenev;

import java.util.ArrayList;
import java.util.List;

public class Archer extends Personage {
    public Archer(String name, int level, int x, int y) {
        super(name, level, x, y);
    }

    @Override
    public List<Personage> chooseEnemiesToFight(List<Personage> enemies){
        List<Personage> selectedEnemies = new ArrayList<>();
        for (Personage enemy : enemies) {
            double range = rangeTo(enemy);
            if (range > 5 && range < 10){
                selectedEnemies.add(enemy);
            }
        }//Как выбрать трех слабых (логика)
        return selectedEnemies;
    }
}
