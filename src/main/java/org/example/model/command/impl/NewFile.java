package org.example.model.command.impl;

import org.example.model.command.Apply;
import org.example.model.command.Commands;

public class NewFile extends Commands implements Apply {
    public NewFile() {
        translation = "Создать новый файл";
    }

    @Override
    public void apply() {
        presenter.createFile();
    }
}
