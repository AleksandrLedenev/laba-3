package ru.amm.ledenev.ui;

import ru.amm.ledenev.dto.request.GetAttackedRequest;
import ru.amm.ledenev.dto.request.NewGameRequest;
import ru.amm.ledenev.dto.request.ReadPersonagesFromFileRequest;
import ru.amm.ledenev.dto.response.ErrorResponse;
import ru.amm.ledenev.dto.response.OkResponse;
import ru.amm.ledenev.dto.response.PersonagesResponse;
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
                    case "exit" -> {
                        System.out.println("До свидания!");
                        System.exit(0);
                    }
                    default -> System.out.println("Неизвестная команда");
                }
            }
        }
    }

    @Override
    public void close() throws Exception {
        consoleInput.close();
    }
    //newgame
    //loadpers из file
    //добавить перса
    //удалить перса
    //поиск
    //вывести кого можно атаковать
    //exit
    //info (список доступных команд)
    //получение всех игроков
}
