package ru.netology.javacore.commands;

import ru.netology.javacore.commands.interfaces.*;

import java.util.ArrayList;
import java.util.List;

/*  Код написал Лыткин Александр Игоревич (aka Serious07) в 2022 г.
    Курсовая работа на тему "Менеджер задач" для Нетологии */
public class CommandsManager {
    private static CommandsManager instance;

    public static CommandsManager getInstance(){
        if(instance == null){
            instance = new CommandsManager();
        }

        return instance;
    }

    private List<Command> commands = new ArrayList();

    public List<Command> getCommands() {
        return commands;
    }
    public void removeCommand(Command command){
        commands.remove(command);
    }

    public void removeLastCommand(){
        commands.remove(getLastElementIndex());
    }

    public Command getLastCommand(){
        return commands.get(getLastElementIndex());
    }

    public CommandUndo getLastUndoCommand(){
        if(commands.size() == 0) return null;

        for(int i = getLastElementIndex(); i >= 0; i--){
            if(commands.get(i) instanceof CommandUndo){
                return (CommandUndo) commands.get(i);
            }
        }

        return null;
    }

    public int getLastElementIndex(){
        return commands.size() - 1;
    }

    public void executeCommand(CommandExecute command){
        if(command instanceof Command){
            commands.add((Command)command);
        }

        command.execute();

        // Дебаг
        // showListOfCommand();
    }

    public String executeCommand(CommandGetStringResult command) {
        return command.getResult();
    }

    private void showListOfCommand(){
        int counter = 0;

        System.out.println("Текущий список команд: ");

        for(Command command : commands){
            if(command instanceof CommandToString){
                System.out.println("Command " + counter + ": " + ((CommandToString) command).commandToString());
            } else {
                System.out.println("Command " + counter);
            }

            counter++;
        }
    }
}
