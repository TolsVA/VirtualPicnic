package org.example.model.command.impl;

import org.example.model.command.Apply;
import org.example.model.command.Commands;

public class WordCount extends Commands implements Apply {
    public WordCount() {
        translation = "Количество слов";
    }

    @Override
    public void apply() {
        presenter.getFruitCounter();
    }
}