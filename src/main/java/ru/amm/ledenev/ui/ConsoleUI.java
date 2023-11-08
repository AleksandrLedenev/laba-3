package ru.amm.ledenev.ui;

import ru.amm.ledenev.dto.request.*;
import ru.amm.ledenev.dto.response.*;
import ru.amm.ledenev.service.RequestProcessor;

import java.util.Scanner;

public class ConsoleUI implements AutoCloseable {

    private final Scanner consoleInput;

    private final RequestProcessor rp;

    public ConsoleUI(){
        this.consoleInput = new Scanner(System.in);
        this.rp = new RequestProcessor();
    }

    public void start(){
        System.out.println("Добро пожаловать! Для запуска игры введите newgame");
        System.out.println("Все команды можно узнать командой info");
        while (true){
            if (consoleInput.hasNext()){
                String command = consoleInput.next();
                switch (command){
                    case "newgame" -> {
                        var request = new NewGameRequest();
                        var response = rp.process(request);
                        if (response instanceof OkResponse){
                            System.out.println("Игра создана...");
                        } else{
                            System.out.println("Ошибка создания игры");
                        }
                    }
                    case "loadfile" -> {
                        System.out.println("Введите название файла");
                        String fileName = consoleInput.next();
                        var request = new ReadPersonagesFromFileRequest(fileName);
                        var response = rp.process(request);
                        if (response instanceof OkResponse){
                            System.out.println("Файл прочитан. Игроки добавлены");
                        }
                        if (response instanceof ErrorResponse){
                            System.out.println("Произошла ошибка: " + ((ErrorResponse) response).message());
                        }
                    }
                    case "getattacked" -> {
                        var request = new GetAttackedRequest();
                        var response = rp.process(request);
                        if (response instanceof PersonagesResponse){
                            System.out.println("Персонажи для атаки: " + ((PersonagesResponse) response).personages());
                        } else if(response instanceof ErrorResponse){
                            System.out.println("Произошла ошибка: " + ((ErrorResponse) response).message());
                        } else {
                            System.out.println("Ошибка получения персонажей");
                        }
                    }
                    case "printpersonages" -> {
                        var request = new GetPersonagesRequest();
                        var response = rp.process(request);
                        if (response instanceof TwoTeamResponse){
                            printPersonages((TwoTeamResponse) response);
                        }
                        if (response instanceof NotFoundResponse){
                            System.out.println("Список персонажей пуст");
                        }
                    }
                    case "info" -> {
                        System.out.println("newgame - создать новую игру");
                        System.out.println("loadfile - загрузить персонажей из файла");
                        System.out.println("getattacked - узнать кого можно атаковать");
                        System.out.println("printpersonages - вывести составы команд");
                        System.out.println("addpersonage - добавить персонажа");
                        System.out.println("deletepersonage - удалить персонажей");
                        System.out.println("exit - закрыть игру");
                    }
                    case "addpersonage" -> {
                        Scanner sc = new Scanner(System.in);
                        System.out.println("Введите данные о персонаже: ");
                        System.out.println("SA *Имя* *Уровень* *Координата Х* *Координата Y*");
                        System.out.println("Первая буква - лучник/мечник (S - лучник, A - мечник)");
                        System.out.println("Вторая буква - союзнник/соперник (A - союзник, E - соперник)");
                        String personageInfo = sc.nextLine();
                        var request = new AddPersonageRequest(personageInfo);
                        var response = rp.process(request);
                        if (response instanceof OkResponse){
                            System.out.println("Персонаж успешно добавлен!");
                        }
                        if (response instanceof ErrorResponse){
                            System.out.println("Произошла ошибка: " + ((ErrorResponse) response).message());
                        }
                    }
                    case "deletepersonage" -> {
                        var printRequest = new GetPersonagesRequest();
                        var printResponse = rp.process(printRequest);
                        if (printResponse instanceof TwoTeamResponse){
                            printPersonages((TwoTeamResponse) printResponse);
                        }
                        if (printResponse instanceof NotFoundResponse){
                            System.out.println("Список персонажей пуст");
                            continue;
                        }

                        System.out.println("Введите имя персонажа:");
                        String name = consoleInput.next();
                        var request = new DeletePersonageRequest(name);
                        var response = rp.process(request);
                        if (response instanceof PersonagesResponse){
                            System.out.println("Персонажи удалены: " + ((PersonagesResponse) response).personages());
                        }
                        if (response instanceof NotFoundResponse){
                            System.out.println("Таких персонажей не найдено");
                        }
                        if (response instanceof ErrorResponse){
                            System.out.println("Произошла ошибка: " + ((ErrorResponse) response).message());
                        }
                    }
                    case "savegame" -> {
                        System.out.println("Введите название файла");
                        String fileName = consoleInput.next();
                        var request = new SaveGameRequest(fileName);
                        var response = rp.process(request);
                        if (response instanceof OkResponse) {
                            System.out.println("Файл сохранен");
                        }
                        if (response instanceof ErrorResponse) {
                            System.out.println("Произошла ошибка: " + ((ErrorResponse) response).message());
                        }
                    }
                    case "loadgame" -> {
                        System.out.println("Введите название файла");
                        String fileName = consoleInput.next();
                        var request = new LoadGameRequest(fileName);
                        var response = rp.process(request);
                        if (response instanceof OkResponse) {
                            System.out.println("Игра загружена");
                        }
                        if (response instanceof ErrorResponse) {
                            System.out.println("Произошла ошибка: " + ((ErrorResponse) response).message());
                        }
                    }
                    case "exit" -> {
                        System.out.println("До свидания!");
                        System.exit(0);
                    }
                    default -> System.out.println("Неизвестная команда");
                }
            }
        }
    }

    private void printPersonages(TwoTeamResponse response){
        System.out.println("Твоя команда: " + response.myTeam());
        System.out.println("Вражеская команда: " + response.enemyTeam());
    }


    @Override
    public void close() throws Exception {
        consoleInput.close();
    }
}
