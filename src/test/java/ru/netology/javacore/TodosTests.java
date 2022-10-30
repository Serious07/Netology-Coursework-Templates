package ru.netology.javacore;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.javacore.commands.CommandsManager;

public class TodosTests {
    @Test
    public void addTaskTest(){
        Todos todos = new Todos();

        todos.addTask("Первое задание");
        todos.addTask("Второе задание");
        todos.addTask("Третье задание");

        Assertions.assertEquals("Второе задание, Первое задание, Третье задание", todos.getAllTasks());
    }

    @Test
    public void removeTaskTest(){
        Todos todos = new Todos();

        todos.addTask("Первое задание");
        todos.addTask("Второе задание");
        todos.addTask("Третье задание");

        todos.removeTask("Второе задание");

        Assertions.assertEquals("Первое задание, Третье задание", todos.getAllTasks());
    }

    @Test
    public void limitAndOrderTest(){
        Todos todos = new Todos();

        todos.addTask("1");
        todos.addTask("7");
        todos.addTask("3");
        todos.addTask("2");
        todos.addTask("4");
        todos.addTask("6");
        todos.addTask("5");
        todos.addTask("9");
        todos.addTask("8");

        Assertions.assertEquals("1, 2, 3, 4, 5, 6, 7", todos.getAllTasks());
    }

    @AfterEach
    public void afterEachTest(){
        CommandsManager.getInstance().reset();
    }
}
