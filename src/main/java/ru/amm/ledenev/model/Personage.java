package ru.amm.ledenev.model;

import java.util.List;
import java.util.Objects;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public abstract class Personage implements Comparable<Personage> {
    private final String name;
    private final int level;
    private final int x;
    private final int y;

    public Personage(String name, int level, int x, int y) {
        this.name = name;
        this.level = level;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public abstract List<Personage> chooseEnemiesToFight(List<Personage> enemies);

    public double rangeTo(Personage personage){
        return sqrt(pow(personage.x - x, 2) + pow(personage.y - y, 2));
    }

    @Override
    public int compareTo(Personage anotherPersonage){
        return Integer.compare(this.level, anotherPersonage.level);
    }

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

    @Override
    public String toString(){
        return name + " " + level;// + " " + x + " " + y;
    }
}
