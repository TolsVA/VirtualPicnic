package org.example.model.command.impl;

import org.example.model.command.Apply;
import org.example.model.command.Commands;
import org.example.model.exception.MyIsEmptyCollectionException;

public class WordFrequency extends Commands implements Apply {
    public WordFrequency() {
        translation = "Частота слов";
    }

    @Override
    public void apply() {
        try {
            presenter.getMapFruits();
        } catch (MyIsEmptyCollectionException e) {
            System.out.println(e.getMessage());
        }
    }
}
