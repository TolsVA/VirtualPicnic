package org.example.model.command.impl;

import org.example.model.command.Apply;
import org.example.model.command.Commands;

public class Read extends Commands implements Apply {
    public Read() {
        translation = "Читать из файла";
    }

    @Override
    public void apply() {
        presenter.readFile();
    }
}
