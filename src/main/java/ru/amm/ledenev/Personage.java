package ru.amm.ledenev;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public abstract class Personage {
    private String name;
    private int level;
    private int x;
    private int y;

    public Personage(String name, int level, int x, int y) {
        this.name = name;
        this.level = level;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract List<Personage> chooseEnemiesToFight(List<Personage> enemies);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personage personage = (Personage) o;
        return level == personage.level && x == personage.x && y == personage.y && Objects.equals(name, personage.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, level, x, y);
    }

    public String toString(){
        return name + " " + level + " " + x + " " + y;
    }

}
