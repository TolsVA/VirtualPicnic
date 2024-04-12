package org.example.model.command.impl;

import org.example.model.command.Apply;
import org.example.model.command.Commands;
import org.example.model.exception.MyIsEmptyCollectionException;

public class LongestWord extends Commands implements Apply {

    public LongestWord() {
        translation = "Самое длинное слово";
    }

    @Override
    public void apply() {
        try {
            presenter.getMaxLengthName();
        } catch (MyIsEmptyCollectionException e) {
            System.out.println(e.getMessage());
        }
    }
}