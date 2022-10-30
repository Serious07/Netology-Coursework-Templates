package ru.netology.javacore.commands.tasks;

import ru.netology.javacore.Todos;
import ru.netology.javacore.commands.interfaces.CommandGetStringResult;
import ru.netology.javacore.commands.interfaces.CommandToString;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*  Код написал Лыткин Александр Игоревич (aka Serious07) в 2022 г.
    Курсовая работа на тему "Менеджер задач" для Нетологии */
public class CommandGetAllTasks extends CommandBaseTasks implements CommandGetStringResult, CommandToString {
    public CommandGetAllTasks(Todos todos) {
        super(todos);
    }

    @Override
    public String getResult() {
        List<String> result = getTasks().stream().
                sorted(Comparator.naturalOrder()).
                collect(Collectors.toList());

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < result.size(); i++){
            stringBuilder.append(result.get(i));
            if(i != result.size() - 1) {
                stringBuilder.append(", ");
            }
        }

        return stringBuilder.toString();
    }

    @Override
    public String commandToString() {
        return "Get all Tasks";
    }
}
