package ru.netology.javacore;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ru.netology.javacore.commands.*;
import ru.netology.javacore.commands.base.CommandRestore;
import ru.netology.javacore.commands.tasks.CommandAddTask;
import ru.netology.javacore.commands.tasks.CommandGetAllTasks;
import ru.netology.javacore.commands.tasks.CommandRemoveTask;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/*  Код написал Лыткин Александр Игоревич (aka Serious07) в 2022 г.
    Курсовая работа на тему "Менеджер задач" для Нетологии */
public class TodoServer {
    private int port;
    private Todos todos;
    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() throws IOException {
        System.out.println("Starting server at " + port + "...");

        try (ServerSocket serverSocket = new ServerSocket(port);) { // стартуем сервер один(!) раз
            while (true) { // в цикле(!) принимаем подключения
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream());
                ) {
                    // Обработка одного подключения
                    String jsonString = in.readLine();

                    System.out.println("Сервер получил строку: " + jsonString);

                    // Формируем JSON из строки и добавляем данные в ProductTracer
                    Gson gson = new Gson();
                    JsonObject dataCommand = gson.fromJson(jsonString, JsonObject.class);

                    if( dataCommand != null ) {
                        String task = dataCommand.get("task").getAsString();
                        String command = dataCommand.get("type").getAsString();

                        if (command.equalsIgnoreCase("ADD")) {
                            CommandsManager.getInstance().executeCommand(new CommandAddTask(todos, task));
                        } else if (command.equalsIgnoreCase("REMOVE")) {
                            CommandsManager.getInstance().executeCommand(new CommandRemoveTask(todos, task));
                        } else if (command.equalsIgnoreCase("RESTORE")){
                            CommandsManager.getInstance().executeCommand(new CommandRestore());
                        }

                        // Подготовка ответа на запрос
                        String answer = CommandsManager.getInstance().executeCommand(new CommandGetAllTasks(todos));

                        System.out.println("Сформированный ответ для клиента: ");
                        System.out.println(answer);

                        out.println(answer);
                    } else {
                        out.println("Сервер принял некорректные данные от клиента!");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
