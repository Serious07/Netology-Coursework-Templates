package ru.netology.javacore;

import java.util.*;

public class Todos {
    List<String> tasks = new ArrayList();
    private final int tasksLimit = 7;

    public void addTask(String task) {
        if(task.length() < tasksLimit) {
            tasks.add(task);
        }
    }

    public void removeTask(String task) {
        tasks.remove(task);
    }

    public String getAllTasks() {
        String[] result = (String[]) tasks.stream().sorted().toArray();

        StringBuilder stringBuilder = new StringBuilder();
        for (String s : result){
            stringBuilder.append(s);
            stringBuilder.append(" ");
        }

        return stringBuilder.toString();
    }

}
