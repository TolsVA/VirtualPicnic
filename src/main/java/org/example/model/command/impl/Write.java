package org.example.model.command.impl;

import org.example.model.command.Apply;
import org.example.model.command.Commands;

public class Write extends Commands implements Apply {
    public Write() {
        translation = "Запись в файл";
    }

    @Override
    public void apply() {
        presenter.writeFile();
    }
}