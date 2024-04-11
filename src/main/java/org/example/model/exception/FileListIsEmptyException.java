package org.example.model.exception;

import java.io.FileNotFoundException;

public class FileListIsEmptyException extends FileNotFoundException/*Exception*/{
    public FileListIsEmptyException(String s) {
        super(s);
    }
}
