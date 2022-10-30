package ru.netology.javacore.commands.tasks;

import ru.netology.javacore.Todos;
import ru.netology.javacore.commands.CommandsManager;
import ru.netology.javacore.commands.base.CommandBase;

import java.util.List;

/*  Код написал Лыткин Александр Игоревич (aka Serious07) в 2022 г.
    Курсовая работа на тему "Менеджер задач" для Нетологии */
public class CommandBaseTasks extends CommandBase {
    private Todos todos;
    private String commandTask;
    private CommandsManager commandsManager;

    protected String getCommandTask() {
        return commandTask;
    }

    public CommandBaseTasks(Todos todos, String task){
        this.todos = todos;
        this.commandTask = task;
        this.commandsManager = CommandsManager.getInstance();
    }

    public CommandBaseTasks(Todos todos){
        this.todos = todos;
        this.commandsManager = CommandsManager.getInstance();
    }

    protected List<String> getTasks() {
        return todos.getTasks();
    }

    protected int getTasksLimit(){
        return todos.getTasksLimit();
    }

    protected void removeTask(String task){
        todos.removeTask(task);
    }

    protected void addTask(String task){
        todos.addTask(task);
    }
}
