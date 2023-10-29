package ru.amm.ledenev.game;


import ru.amm.ledenev.model.Personage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FightGame {
    private final List<Personage> myTeam;
    private final List<Personage> enemyTeam;

    public FightGame(){
        this.myTeam = new ArrayList<>();
        this.enemyTeam = new ArrayList<>();
    }

    public Set<Personage> getAttackedEnemies(){
        Set<Personage> attackedEnemies = new HashSet<>();
        for (Personage teammate : myTeam) {
            attackedEnemies.addAll(teammate.chooseEnemiesToFight(enemyTeam));
        }
        return attackedEnemies;
    }

    public void addPersonageToMyTeam(Personage personage){
        myTeam.add(personage);
    }

    public void addPersonageToEnemyTeam(Personage personage){
        enemyTeam.add(personage);
    }
}
