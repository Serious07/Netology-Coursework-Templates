package ru.netology.javacore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static int port = 8989;
    private static String ip = "localhost";

    public static void main(String[] args) {
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
                } else if (subSplitCommand.length > 1 && subSplitCommand[0].equalsIgnoreCase("add")) {
                    String command = "";
                    String task = "";

                    if(subSplitCommand.length >= 1){
                        command = subSplitCommand[0].toUpperCase();
                    }

                    if(subSplitCommand.length >= 2){
                        task = subSplitCommand[1];
                    }

                    String jsonString = "{ \"type\": \"" + command + "\", \"task\": \"" + task + "\" }";
                    out.println(jsonString);
                    System.out.println("Данные переданы на сервер.");

                    System.out.println("Ответ от сервера:");
                    System.out.println(in.readLine());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
