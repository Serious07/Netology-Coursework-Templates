package ru.netology.javacore.commands.tasks;

import ru.netology.javacore.Todos;

/*  Код написал Лыткин Александр Игоревич (aka Serious07) в 2022 г.
    Курсовая работа на тему "Менеджер задач" для Нетологии */
public class CommandAddTask extends CommandBaseTasks {
    public CommandAddTask(Todos todos, String task) {
        super(todos, task);
    }

    @Override
    public void execute() {
        if(getTasks().size() < getTasksLimit()) {
            addTask(getCommandTask());
        } else {
            removeCommand(this);
        }
    }

    @Override
    public void undo() {
        if(getTasks().contains(getCommandTask())) {
            removeTask(getCommandTask());
        }

        removeCommand(this);
    }

    @Override
    public String commandToString() {
        return "Command ADD Task: " + getCommandTask();
    }

    @Override
    public boolean isHasUndo() {
        return true;
    }
}
