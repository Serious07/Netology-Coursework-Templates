package ru.netology.javacore.commands.base;

import ru.netology.javacore.commands.interfaces.Command;
import ru.netology.javacore.commands.interfaces.CommandExecute;
import ru.netology.javacore.commands.interfaces.CommandToString;
import ru.netology.javacore.commands.interfaces.CommandUndo;

/*  Код написал Лыткин Александр Игоревич (aka Serious07) в 2022 г.
    Курсовая работа на тему "Менеджер задач" для Нетологии */
public class CommandRestore extends CommandBase {
    @Override
    public void execute() {
        getLastUndoCommand().undo();
        removeCommand(this);
    }

    @Override
    public String commandToString() {
        return "Restore";
    }

    @Override
    public boolean isHasUndo() {
        return false;
    }
}
