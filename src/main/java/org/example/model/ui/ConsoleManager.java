package org.example.model.ui;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class ConsoleManager implements UserUi {
    private static final Scanner scanner;

    static {
        scanner = new Scanner(in);
    }

    @Override
    public String prompt(String message) {
        out.print(message);
        return scanner.nextLine();
    }
}