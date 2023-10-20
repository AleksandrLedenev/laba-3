package ru.amm.ledenev;

import java.util.List;

public class Archer extends Personage {
    public Archer(String name, int level, int x, int y) {
        super(name, level, x, y);
    }

    @Override
    public List<Personage> chooseEnemiesToFight(List<Personage> enemies){
        return null;
    }
}
