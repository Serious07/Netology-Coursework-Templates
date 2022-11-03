package ru.netology.javacore.commands.tasks;

import ru.netology.javacore.Todos;

/*  Код написал Лыткин Александр Игоревич (aka Serious07) в 2022 г.
    Курсовая работа на тему "Менеджер задач" для Нетологии */
public class CommandRemoveTask extends CommandBaseTasks {
    public CommandRemoveTask(Todos todos, String task) {
        super(todos, task);
    }

    @Override
    public void execute() {
        removeTask(getCommandTask());
    }

    @Override
    public void undo() {
        addTask(getCommandTask());
        removeCommand(this);
    }

    @Override
    public String commandToString() {
        return "Command REMOVE Task: " + getCommandTask();
    }

    @Override
    public boolean isHasUndo() {
        return true;
    }
}
