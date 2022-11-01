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

    public void reset(){
        commands.clear();
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
        if(commands.isEmpty()) return null;

        CommandUndo commandUndo;

        for(int i = getLastElementIndex(); i >= 0; i--){
            try{
                commandUndo = (CommandUndo) commands.get(i);
                return commandUndo;
            }catch (ClassCastException ex){

            }
        }

        return null;
    }

    public int getLastElementIndex(){
        return commands.size() - 1;
    }

    public void executeCommand(CommandExecute command){
        try {
            Command baseCommand = (Command) command;
            commands.add(baseCommand);
        } catch (ClassCastException ex){

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

        CommandToString commandToString;

        for(Command command : commands){
            try{
                commandToString = (CommandToString) command;
                System.out.println("Command " + counter + ": " + commandToString.commandToString());
            } catch (ClassCastException ex){
                System.out.println("Command " + counter);
            }

            counter++;
        }
    }
}
