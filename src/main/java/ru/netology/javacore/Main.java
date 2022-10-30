package ru.netology.javacore;

import java.io.IOException;

public class Main {

    /*  Код написал Лыткин Александр Игоревич (aka Serious07) в 2022 г.
        Курсовая работа на тему "Менеджер задач" для Нетологии */
    public static void main(String[] args) throws IOException {
        Todos todos = new Todos();
        TodoServer server = new TodoServer(8989, todos);
        server.start();
    }
}
