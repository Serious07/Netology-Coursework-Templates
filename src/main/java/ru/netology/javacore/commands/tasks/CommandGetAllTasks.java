package ru.netology.javacore.commands.tasks;

import ru.netology.javacore.Todos;

/*  Код написал Лыткин Александр Игоревич (aka Serious07) в 2022 г.
    Курсовая работа на тему "Менеджер задач" для Нетологии */
public class CommandGetAllTasks extends CommandBaseTasks {
    public CommandGetAllTasks(Todos todos) {
        super(todos);
    }

    @Override
    public String getResult() {
        return getAllTasks();
    }

    @Override
    public String commandToString() {
        return "Get all Tasks";
    }
}
