package ru.netology.javacore;

import ru.netology.javacore.commands.CommandsManager;
import ru.netology.javacore.commands.tasks.CommandGetAllTasks;

import java.util.*;

/*  Код написал Лыткин Александр Игоревич (aka Serious07) в 2022 г.
    Курсовая работа на тему "Менеджер задач" для Нетологии */
public class Todos {
    private List<String> tasks = new ArrayList();
    private int tasksLimit = 7;

    // Getters
    public List<String> getTasks() {
        return tasks;
    }

    public void removeTask(String task){
        tasks.remove(task);
    }

    public void addTask(String task){
        if(tasks.size() < tasksLimit) {
            tasks.add(task);
        }
    }

    public String getAllTasks(){
        return CommandsManager.getInstance().executeCommand(new CommandGetAllTasks(this));
    }

    public int getTasksLimit() {
        return tasksLimit;
    }
}
