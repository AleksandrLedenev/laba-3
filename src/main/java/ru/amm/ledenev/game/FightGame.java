package ru.amm.ledenev.game;


import ru.amm.ledenev.model.Personage;

import java.util.*;

public class FightGame {
    private final List<Personage> myTeam;
    private final List<Personage> enemyTeam;

    public FightGame(){
        this.myTeam = new ArrayList<>();
        this.enemyTeam = new ArrayList<>();
    }

    public List<Personage> getMyTeam() {
        return myTeam;
    }

    public List<Personage> getEnemyTeam() {
        return enemyTeam;
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

    public List<Personage> deletePersonageToMyTeam(String name){
        List<Personage> deletePersonage = new ArrayList<>();
        for (Personage teammate : myTeam) {
            String tempName = teammate.getName();
            if (Objects.equals(tempName, name)){
                deletePersonage.add(teammate);
            }
        }
        for (Personage deleteTeammate : deletePersonage) {
            myTeam.remove(deleteTeammate);
        }
        return deletePersonage;
    }

    public List<Personage> deletePersonageToEnemyTeam(String name){
        List<Personage> deletePersonage = new ArrayList<>();
        for (Personage teammate : enemyTeam) {
            String tempName = teammate.getName();
            if (Objects.equals(tempName, name)){
                deletePersonage.add(teammate);
            }
        }
        for (Personage deleteTeammate : deletePersonage) {
            enemyTeam.remove(deleteTeammate);
        }
        return deletePersonage;
    }
}
