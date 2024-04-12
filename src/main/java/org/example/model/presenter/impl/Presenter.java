package org.example.model.presenter.impl;

import org.example.bd.WorksWithDatabase;
import org.example.model.command.CommandsEnum;
import org.example.model.exception.FileListIsEmptyException;
import org.example.model.exception.FileNotCreatedException;
import org.example.model.exception.MyIsEmptyCollectionException;
import org.example.model.presenter.Presentable;
import org.example.model.ui.UserUi;
import org.example.view.Printable;

import java.io.IOException;
import java.util.*;

public class Presenter implements Presentable {
    private final UserUi consoleManager;
    private final WorksWithDatabase database;
    private final Printable view;
    private int fruitCounter = 0;
    private final Map<String, Integer> mapFruits = new HashMap<>();
    private final List<String> listFruits = new ArrayList<>();
    private List<String> listFile;

    public Presenter(UserUi consoleManager, Printable view, WorksWithDatabase database) {
        this.consoleManager = consoleManager;
        this.view = view;
        this.database = database;
    }

    @Override
    public void readFile() {
        try {
            listFile = database.showListOfFiles();
        } catch (FileListIsEmptyException e) {
            view.print(e.getMessage());
            return;
        }

        readAll(getFileName());
    }

    @Override
    public void writeFile() {
        try {
            listFile = database.showListOfFiles();
        } catch (FileListIsEmptyException e) {
            view.print(e.getMessage());
            return;
        }
        String fileName = getFileName();
        String text = prompt("Введите текст: ");

        List<String> list = Arrays.stream(CommandsEnum.values())
                .map(c -> c + " -> (" + c.getTranslation() + ")")
                .toList();

        String command = prompt("Список команд: -> " + list + "\nВведите команду : ").toUpperCase();
        while (!isContainsCommand(command)) {
            view.print("Такой команды нет!!!\nСписок команд: -> " + list);
            command = prompt("Введите команду: ").toUpperCase();
        }
        CommandsEnum com = CommandsEnum.valueOf(command);

        try {
            database.writeFile(fileName, text, com.getRecordingMode());
        } catch (FileNotCreatedException e) {
            view.print(e.getMessage());
        }
    }

    @Override
    public void createFile() {
        String fileName = prompt("Введите имя файла без расширения: ") + ".txt";
        try {
            database.writeFile(fileName, "", true);
        } catch (FileNotCreatedException e) {
            view.print(e.getMessage());
            return;
        }
        view.print(String.format("Файл %s успешно создан", fileName));
    }

    @Override
    public void setIndex(int index) {
        view.setIndex(index);
    }


    private boolean isContainsCommand(String commandName) {
        CommandsEnum[] commands= CommandsEnum.values();
        for (CommandsEnum command : commands) {
            if (command.toString().equals(commandName)) {
                return true;
            }
        }
        return false;
    }

    private String getFileName() {
        String fileName = prompt(String.format("%s%nВведите имя файла: ", listFile));
        while (!isContainsFile(fileName)) {
            fileName = prompt(
                    String.format("Имя файла должно соответствовать имени из списка " +
                            "например имя.txt %s%nВведите имя файла: ", listFile)
            );
        }
        return fileName;
    }

    private boolean isContainsFile(String fileName) {
        for (String name : listFile) {
            if (fileName.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void readAll(String fileName) {
        String textFile;
        try {
            textFile = database.readAll(fileName);
        } catch (IOException e) {
            view.print(e.getMessage() + ". Устраните проблему. И попробуйте снова.");
            return;
        }

        Collections.addAll(listFruits, textFile.split(" ") );
        fullMapFruits();
        view.print(String.format("Содержимое файла -> (%s)", textFile));

        setIndex(1);
    }

    @Override
    public void getMapFruits() {
        view.print(mapFruits.toString());
    }

    private void fullMapFruits(){
        if (listFruits.isEmpty())
            throw new MyIsEmptyCollectionException("Эта коллекция пустая");
        for (String listFruit : listFruits) {
            String fruitName = listFruit.trim();
            if (!fruitName.isEmpty()) {
                if (mapFruits.containsKey(listFruit)) {
                    mapFruits.put(listFruit, mapFruits.get(listFruit) + 1);
                } else {
                    mapFruits.put(listFruit, 1);
                }
                fruitCounter++;
            }
        }
    }

    @Override
    public void getFruitCounter() {
        view.print(String.format("Общее количество слов: %d",fruitCounter));
    }

    @Override
    public void getMaxLengthName() {
        int length = 0;
        for (String key : mapFruits.keySet()) {
            if (length < key.length()) {
                length = key.length();
            }
        }
        List<String> nameFruits = new ArrayList<>();
        for (String key : mapFruits.keySet()) {
            if (length == key.length()) {
                nameFruits.add(key);
            }
        }

        view.print(
                String.format(
                        "Самое длинное название фрукта(ов): %s имеет %d символов%n",
                        nameFruits,
                        nameFruits.get(0).length()
                )
        );
    }

    @Override
    public String prompt(String message) {
        return consoleManager.prompt(message);
    }
}
