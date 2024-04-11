package org.example;

import org.example.bd.impl.Database;
import org.example.model.command.*;
import org.example.model.command.impl.*;
import org.example.model.presenter.Presentable;
import org.example.model.presenter.impl.Presenter;
import org.example.model.ui.ConsoleManager;
import org.example.view.impl.View;

import java.util.ArrayList;
import java.util.List;

public class App {
    private static App instance;

    private App() {
        View view = new View();
        Presentable presenter = new Presenter(new ConsoleManager(), view, new Database());
        Commands.setPresenter(presenter);
        view.run(initCommands());
    }

    public static void run() {
        if (instance == null) {
            instance = new App();
        }
    }

    private List<List<Commands>> initCommands() {
        Commands read = new Read();
        Commands write = new Write();
        Commands newFile = new NewFile();
        Commands exit = new Exit();

        Commands longestWord = new LongestWord();
        Commands quantityByName = new WordFrequency();
        Commands wordCount = new WordCount();
        Commands mainMenu = new MainMenu();

        List<List<Commands>> commands = new ArrayList<>();
        commands.add(new ArrayList<>(List.of(read, write, newFile, exit)));
        commands.add(new ArrayList<>(List.of(longestWord, quantityByName, wordCount, mainMenu, exit)));

        return commands;
    }
}
