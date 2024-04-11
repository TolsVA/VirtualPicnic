package org.example.view.impl;

import org.example.model.command.Apply;
import org.example.model.command.Commands;
import org.example.view.Printable;

import java.util.*;

public class View implements Printable {
    private List<List<Commands>> commands;
    private int index = 0;

    public void run(List<List<Commands>> commands) {
        this.commands = commands;
        Commands com;
        while (true) {
            print("Список команд: -> " + printClassName(this.commands));
            String command = Commands.prompt("Введите команду: ").toUpperCase();
            while (!isContains(command)) {
                print("Такой команды нет!!!\nСписок команд: -> " + printClassName(this.commands) + "\n");
                command = Commands.prompt("Введите команду: ").toUpperCase();
            }
            com = getCommands(command);
            if (com != null) {
                if (com.getClass().getSimpleName().equalsIgnoreCase("EXIT")) return;
                if (com instanceof Apply c) c.apply();
            }
        }
    }

    private List<String> printClassName(List<List<Commands>> commands) {
        List<String> list = new ArrayList<>();
        for (Commands command : commands.get(index)) {
            list.add(String.format(
                    "%s (%s)",
                    command.getClass().getSimpleName().toUpperCase(),
                    command.getTranslation())
            );
        }
        return list;
    }

    private Commands getCommands(String strCommand) {
        for (Commands value : commands.get(index)) {
            if (strCommand.equals(value.getClass().getSimpleName().toUpperCase())) {
                return value;
            }
        }
        return null;
    }

    private boolean isContains(String command) {
        for (Commands value : commands.get(index)) {
            if (command.equals(value.getClass().getSimpleName().toUpperCase())) {
                return true;
            }
        }
        return false;
    }

//        Map<String, Integer> mapFruits = presenter.getMapFruits(listFruits);
//        System.out.println(mapFruits);
//        Map.Entry<String, Integer> maxEntry = Collections.max(mapFruits.entrySet(), Map.Entry.comparingByValue());
//        for (var entry : mapFruits.entrySet()) {
//            System.out.println(entry);
//        }
//        System.out.printf("%s: самое большое количество фруктов: %d%n", maxEntry.getKey(), maxEntry.getValue());
//
//
//        System.out.println("Общее количество слов: " + presenter.getFruitCounter());
//        System.out.println("Количество наименований фруктов: " + mapFruits.size());


    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public void setIndex(int i) {
        index = i;
    }
}
