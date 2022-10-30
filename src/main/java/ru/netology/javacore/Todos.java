package ru.netology.javacore;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {
    List<String> tasks = new ArrayList();
    private final int tasksLimit = 7;

    public void addTask(String task) {
        if(tasks.size() < tasksLimit) {
            tasks.add(task);
        }
    }

    public void removeTask(String task) {
        tasks.remove(task);
    }

    public String getAllTasks() {
        List<String> result = tasks.stream().
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

}
