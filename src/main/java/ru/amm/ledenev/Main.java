package ru.amm.ledenev;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException{
        Scanner scanner = new Scanner(new File("./text.txt"));
        List<Personage> myTeam = new ArrayList<>();
        List<Personage> enemyTeam = new ArrayList<>();
        while (scanner.hasNext()){
            String marker = scanner.next();
            String name = scanner.next();
            int level = scanner.nextInt();
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            Personage personage;
            switch (marker.charAt(0)) {
                case 'S' -> personage = new Swordsman(name, level, x, y);
                case 'A' -> personage = new Archer(name, level, x, y);
                default -> personage = null;
            }

            List<Personage> team;
            switch (marker.charAt(1)) {
                case 'A' -> team = myTeam;
                case 'E' -> team = enemyTeam;
                default -> team = null;
            }

            team.add(personage);
        }
        Game game = new Game(myTeam, enemyTeam);
        System.out.println(myTeam);
        System.out.println(enemyTeam);
        System.out.println(game.getAttackedEnemies());
    }

}

//ДЗ: чтение из файла. Реализация лучника, мечника.
//Создать писюна который атакует всех с большим, чем у него лвл в радиусе 3х метров
//вывести имена атакованных противников(или что нибудь другое)
