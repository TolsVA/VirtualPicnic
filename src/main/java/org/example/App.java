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
        Commands exit = new Exit();
        List<List<Commands>> commands = new ArrayList<>();
        commands.add(new ArrayList<>(List.of(new Read(), new Write(), new NewFile(), exit)));
        
        commands.add(new ArrayList<>(
                List.of(
                        new LongestWord(),
                        new WordFrequency(),
                        new WordCount(),
                        new MostCommonWord(),
                        new MainMenu(),
                        exit
                )
        ));
        return commands;
    }
}