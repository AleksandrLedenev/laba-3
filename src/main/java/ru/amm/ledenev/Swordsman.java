package ru.amm.ledenev;

import java.util.List;

public class Swordsman extends Personage {
    public Swordsman(String name, int level, int x, int y) {
        super(name, level, x, y);
    }

    @Override
    public List<Personage> chooseEnemiesToFight(List<Personage> enemies){
        return enemies.stream()
                .filter((enemy) -> rangeTo(enemy) < 2)
                .toList();
    }
}
