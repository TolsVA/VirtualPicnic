package org.example.model.command;

import org.example.model.presenter.Presentable;

public abstract class Commands {
    protected static Presentable presenter;
    protected String translation;
    public static String prompt(String message){
        return presenter.prompt(message);
    }
    public static void setPresenter(Presentable presenter) {
        Commands.presenter = presenter;
    }

    public String getTranslation() {
        return translation;
    }
}
