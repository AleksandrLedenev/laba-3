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
            Personage personage;
            String marker = scanner.next();
            String name = scanner.next();
            int level = scanner.nextInt();
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            switch (marker){
                case "SA": personage = new Swordsman(name, level, x, y);
                    myTeam.add(personage);
                    break;
                case "SE": personage = new Swordsman(name, level, x, y);
                    enemyTeam.add(personage);
                    break;
                case "AA": personage = new Archer(name, level, x, y);
                    myTeam.add(personage);
                    break;
                case "AE": personage = new Archer(name, level, x, y);
                    enemyTeam.add(personage);
                    break; //Уместен ли break? и вообще красиво или фигня?
            }
        }
        Game game = new Game(myTeam, enemyTeam);
        System.out.println(myTeam.toString());
        System.out.println(enemyTeam.toString());
        System.out.println(game.getAttackedEnemies());
    }

}

//ДЗ: чтение из файла. Реализация лучника, мечника.
//Создать писюна который атакует всех с большим, чем у него лвл в радиусе 3х метров
//вывести имена атакованных противников(или что нибудь другое)
