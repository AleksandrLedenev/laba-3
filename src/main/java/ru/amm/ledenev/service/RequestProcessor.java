package ru.amm.ledenev.service;

import ru.amm.ledenev.dto.request.*;
import ru.amm.ledenev.dto.response.*;
import ru.amm.ledenev.game.FightGame;
import ru.amm.ledenev.model.Personage;
import ru.amm.ledenev.model.impl.Archer;
import ru.amm.ledenev.model.impl.Swordsman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class RequestProcessor {

    private FightGame game;
    public Response process(Request request){
        switch (request){
            case NewGameRequest r -> {
                game = new FightGame();
                return new OkResponse();
            }
            case ReadPersonagesFromFileRequest r -> {
                if (game == null){
                    return new ErrorResponse("Игра не инициализирована");
                }
                try{
                    readFromFile(r.fileName());
                } catch (FileNotFoundException ex){
                    return new ErrorResponse("Не найден файл");
                } catch (IllegalArgumentException ex){
                    return new ErrorResponse(ex.getMessage());
                }
                return new OkResponse();
            }
            case GetAttackedRequest r -> {
                if (game == null){
                    return new ErrorResponse("Игра не инициализирована");
                }
                var personages = game.getAttackedEnemies();
                return new PersonagesResponse(personages);
            }
            case PrintPersonagesRequest r -> {
                if (game == null){
                    return new ErrorResponse("Игра не инициализирована");
                }
                var myTeam = game.getMyTeam();
                var enemyTeam = game.getEnemyTeam();
                return new TwoTeamResponse(myTeam, enemyTeam);
            }
            case AddPersonageRequest r -> {
                if (game == null){
                    return new ErrorResponse("Игра не инициализирована");
                }
                try {
                    readPersonage(r.personageInfo());
                } catch (IllegalArgumentException ex){
                    return new ErrorResponse(ex.getMessage());
                }
                return new OkResponse();
            }
            case DeletePersonageRequest r -> {
                if (game == null){
                    return new ErrorResponse("Игра не инициализирована");
                }
                List<Personage> deletePersonage = new ArrayList<>();
                deletePersonage.addAll(game.deletePersonageToMyTeam(r.name()));
                deletePersonage.addAll(game.deletePersonageToEnemyTeam(r.name()));
                if (deletePersonage.size() == 0){
                    return new ErrorResponse("Такого игрока не найдено");
                }
                return new DeleteResponse(deletePersonage);
            }
        }
    }

    private void readFromFile(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        while (scanner.hasNext()){
            readPersonage(scanner.nextLine());
        }
        scanner.close();
    }

    private void readPersonage(String personageInfo){
        String[] subStr = personageInfo.split("\\ ");
        String marker = subStr[0];
        String name = subStr[1];
        int level = Integer.parseInt(subStr[2]);
        int x = Integer.parseInt(subStr[3]);
        int y = Integer.parseInt(subStr[4]);

        Personage personage;
        switch (marker.charAt(0)) {
            case 'S' -> personage = new Swordsman(name, level, x, y);
            case 'A' -> personage = new Archer(name, level, x, y);
            default -> throw new IllegalArgumentException("Неправильный ввод персонажа");
        }

        switch (marker.charAt(1)) {
            case 'A' -> game.addPersonageToMyTeam(personage);
            case 'E' -> game.addPersonageToEnemyTeam(personage);
            default -> throw new IllegalArgumentException("Неправильная команда персонажа");
        }
    }
}
