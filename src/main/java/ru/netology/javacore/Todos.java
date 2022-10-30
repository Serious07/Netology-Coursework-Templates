package ru.netology.javacore;

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
        tasks.add(task);
    }

    public int getTasksLimit() {
        return tasksLimit;
    }
}
