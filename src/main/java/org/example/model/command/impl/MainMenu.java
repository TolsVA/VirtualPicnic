package org.example.model.command.impl;

import org.example.model.command.Apply;
import org.example.model.command.Commands;

public class MainMenu extends Commands implements Apply {
    public MainMenu() {
        translation = "Главное меню";
    }

    @Override
    public void apply() {
        presenter.setIndex(0);
    }
}
