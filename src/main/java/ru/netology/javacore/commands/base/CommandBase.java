package ru.netology.javacore.commands.base;

import ru.netology.javacore.commands.CommandsManager;
import ru.netology.javacore.commands.interfaces.Command;
import ru.netology.javacore.commands.interfaces.CommandUndo;

/*  Код написал Лыткин Александр Игоревич (aka Serious07) в 2022 г.
    Курсовая работа на тему "Менеджер задач" для Нетологии */
public class CommandBase implements Command {
    private CommandsManager commandsManager;

    public CommandBase(){
        commandsManager = CommandsManager.getInstance();
    }

    public void removeCommand(Command command){
        commandsManager.removeCommand(command);
    }

    public void removeLastCommand(){
        commandsManager.removeLastCommand();
    }

    public Command getLastCommand(){
        return commandsManager.getLastCommand();
    }

    public CommandUndo getLastUndoCommand(){
        return commandsManager.getLastUndoCommand();
    }
}
