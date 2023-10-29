package ru.amm.ledenev.service;

import ru.amm.ledenev.dto.request.GetAttackedRequest;
import ru.amm.ledenev.dto.request.NewGameRequest;
import ru.amm.ledenev.dto.request.ReadPersonagesFromFileRequest;
import ru.amm.ledenev.dto.request.Request;
import ru.amm.ledenev.dto.response.ErrorResponse;
import ru.amm.ledenev.dto.response.OkResponse;
import ru.amm.ledenev.dto.response.PersonagesResponse;
import ru.amm.ledenev.dto.response.Response;
import ru.amm.ledenev.game.FightGame;
import ru.amm.ledenev.model.Personage;
import ru.amm.ledenev.model.impl.Archer;
import ru.amm.ledenev.model.impl.Swordsman;

import java.io.File;
import java.io.FileNotFoundException;
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
        }
    }

    private void readFromFile(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
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
                default -> throw new IllegalArgumentException("Неправильный ввод персонажа");
            }

            switch (marker.charAt(1)) {
                case 'A' -> game.addPersonageToMyTeam(personage);
                case 'E' -> game.addPersonageToEnemyTeam(personage);
                default -> throw new IllegalArgumentException("Неправильная команда персонажа");
            }
        }
        scanner.close();
    }
}
