package org.example.model.exception;

import java.io.IOException;

public class FileNotCreatedException extends IOException {
    public FileNotCreatedException(String s) {
        super(s);
    }
}
