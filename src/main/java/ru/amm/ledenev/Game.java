package ru.amm.ledenev;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private final List<Personage> myTeam;
    private final List<Personage> enemyTeam;

    public Game(List<Personage> myTeam, List<Personage> enemyTeam) {
        this.myTeam = myTeam;
        this.enemyTeam = enemyTeam;
    }

    public Set<Personage> getAttackedEnemies(){
        Set<Personage> attackedEnemies = new HashSet<>();
        for (Personage teammate : myTeam) {
            attackedEnemies.addAll(teammate.chooseEnemiesToFight(enemyTeam));
        }
        return attackedEnemies;
    }
}
