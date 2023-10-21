package ru.amm.ledenev;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;

public class Swordsman extends Personage {
    public Swordsman(String name, int level, int x, int y) {
        super(name, level, x, y);
    }


    @Override
    public List<Personage> chooseEnemiesToFight(List<Personage> enemies){
        List<Personage> SelectedEnemies = new ArrayList<>();
        for (Personage enemy:enemies) {
        }
        //как взять координаты Swordsman или Archer?
        return SelectedEnemies;
        }
}
