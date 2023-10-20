package ru.amm.ledenev;


import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class Game {
    private List<Personage> myTeam;
    private List<Personage> enemyTeam;

    public Game(List<Personage> myTeam, List<Personage> enemyTeam) {
        this.myTeam = myTeam;
        this.enemyTeam = enemyTeam;
    }

    public Collection<Personage> getAttackedEnemies(){
        Collection<Personage> myCollection = new HashSet<>();
        for (Personage myTeammate : myTeam) {
            myCollection.addAll(myTeammate.chooseEnemiesToFight(enemyTeam));
        }
        return myCollection;
    }
}
