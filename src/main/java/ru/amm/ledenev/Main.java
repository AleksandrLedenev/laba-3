package ru.amm.ledenev;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(
                List.of(new Archer("Вася", 2, 2, 2)),
                List.of(new Swordsman("Петя", 3, 1, 1))
        );
        System.out.println(game.getAttackedEnemies());
    }
}