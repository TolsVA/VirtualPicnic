package org.example.model.command.impl;

import org.example.model.command.Apply;
import org.example.model.command.Commands;

public class MostCommonWord extends Commands implements Apply {
    public MostCommonWord() {
        translation = "Самое частое слово";
    }

    @Override
    public void apply() {
        presenter.maxEntry();
    }
}