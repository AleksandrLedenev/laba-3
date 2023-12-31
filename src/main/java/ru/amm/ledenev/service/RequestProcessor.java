package ru.amm.ledenev.service;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.amm.ledenev.dto.request.*;
import ru.amm.ledenev.dto.response.*;
import ru.amm.ledenev.game.FightGame;
import ru.amm.ledenev.model.Personage;
import ru.amm.ledenev.model.impl.Archer;
import ru.amm.ledenev.model.impl.Swordsman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RequestProcessor {

    public FightGame game;
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
            case GetPersonagesRequest r -> {
                if (game == null){
                    return new ErrorResponse("Игра не инициализирована");
                }
                var myTeam = game.getMyTeam();
                var enemyTeam = game.getEnemyTeam();
                if (myTeam.isEmpty() && enemyTeam.isEmpty()){
                    return new NotFoundResponse();
                }
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
                List<Personage> deletedPersonages = new ArrayList<>();
                String name = r.name();
                deletedPersonages.addAll(game.deletePersonageFromMyTeam(name));
                deletedPersonages.addAll(game.deletePersonageFromEnemyTeam(name));
                if (deletedPersonages.isEmpty()){
                    return new NotFoundResponse();
                }
                return new PersonagesResponse(deletedPersonages);
            }
            case SaveGameRequest r -> {
                if (game == null){
                    return new ErrorResponse("Игра не инициализирована");
                }
                try{
                    String path = "src/save/" + r.fileName() + ".json";
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.writeValue(new File(path), game);
                } catch (IllegalArgumentException ex){
                    return new ErrorResponse(ex.getMessage());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return new OkResponse();
            }
            case LoadGameRequest r -> {
                try{
                    String path = "src/save/" + r.fileName() + ".json";
                    ObjectMapper mapper = new ObjectMapper();
                    game = mapper.readValue(new File(path), new TypeReference<>(){});
                } catch (FileNotFoundException ex){
                    return new ErrorResponse("Не найден файл");
                } catch (IllegalArgumentException ex){
                    return new ErrorResponse(ex.getMessage());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return new OkResponse();
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
        String[] subStr = personageInfo.split(" ");
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
