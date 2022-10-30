package ru.netology.javacore;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

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
                            todos.addTask(task);
                        } else if (command.equalsIgnoreCase("REMOVE")) {
                            todos.removeTask(task);
                        }

                        // Подготовка ответа на запрос
                        String answer = todos.getAllTasks();

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
