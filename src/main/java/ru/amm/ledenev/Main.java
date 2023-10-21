package ru.amm.ledenev;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./text.txt"));
        List<Personage> myTeam = new ArrayList<>();
        List<Personage> enemyTeam = new ArrayList<>();
        while (scanner.hasNext()){
           Personage personage = new Archer(scanner.next(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
            if (scanner.next() == "A"){
                myTeam.add(personage);
            }
            else {
                enemyTeam.add(personage);
            }
        }

        System.out.println(myTeam.toString());
//не очень понимаю как можно все это совмесить в одно целое, чтобы было красиво и функционально

/*
        Game game = new Game(
                List.of(new Archer("Вася", 2, 2, 2)),
                List.of(new Swordsman("Петя", 3, 1, 1))
        );
        System.out.println(game.getAttackedEnemies());*/
    }
}

//ДЗ: чтение из файла. Реализация лучника, мечника.
//Создать писюна который атакует всех с большим, чем у него лвл в радиусе 3х метров
//вывести имена атакованных противников(или что нибудь другое)
