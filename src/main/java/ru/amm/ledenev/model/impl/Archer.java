package ru.amm.ledenev.model.impl;

import ru.amm.ledenev.model.Personage;

import java.util.List;

public class Archer extends Personage {
    public Archer(String name, int level, int x, int y) {
        super(name, level, x, y);
    }

    @Override
    public List<Personage> chooseEnemiesToFight(List<Personage> enemies){
        return enemies.stream()
                .filter((enemy) -> {
                    double range = rangeTo(enemy);
                    return range > 5 && range < 10;
                })
                .sorted()
                .limit(3)
                .toList();
    }
}
