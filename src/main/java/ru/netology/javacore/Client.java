package ru.netology.javacore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*  Код написал Лыткин Александр Игоревич (aka Serious07) в 2022 г.
    Курсовая работа на тему "Менеджер задач" для Нетологии */

public class Client {
    private static int port = 8989;
    private static String ip = "localhost";

    public static void main(String[] args) {
        welcomeMessage();

        while (true) {
            try (Socket clientSocket = new Socket(ip, port);
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                String currentCommand = reader.readLine();
                String[] subSplitCommand = currentCommand.split(" ");

                if (currentCommand.equalsIgnoreCase("end") ||
                        currentCommand.equalsIgnoreCase("exit") ||
                        currentCommand.equalsIgnoreCase("quit")) {
                    System.out.println("Программа остановлена");
                    break;
                } else if (subSplitCommand.length >= 1) {
                    String command = subSplitCommand[0].toUpperCase();;
                    String task = getTask(subSplitCommand);

                    String jsonString = "{ \"type\": \"" + command + "\", \"task\": \"" + task + "\" }";
                    out.println(jsonString);
                    System.out.println("Ответ от сервера:");
                    System.out.println(in.readLine());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static String getTask(String[] fullCommand){
        if (fullCommand.length <= 1) return "";

        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 1; i < fullCommand.length; i++){
            stringBuilder.append(fullCommand[i]);
            if (i != (fullCommand.length - 1)) {
                stringBuilder.append(" ");
            }
        }

        return stringBuilder.toString();
    }

    private static void welcomeMessage(){
        System.out.println("        Менеджер задач      ");
        System.out.println("============================");
        System.out.println("      Доступные команды:    ");
        System.out.println("============================");
        System.out.println("add [Задача] - Добавить задачу");
        System.out.println("remove [Задача] - Удалить задачу");
        System.out.println("restore - Отменить последнее действие");
    }
}
