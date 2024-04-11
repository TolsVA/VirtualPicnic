package org.example.bd;

import org.example.model.exception.FileListIsEmptyException;
import org.example.model.exception.FileNotCreatedException;

import java.io.IOException;
import java.util.List;

public interface WorksWithDatabase {
    String readAll(String fileName) throws IOException;
    List<String> showListOfFiles() throws FileListIsEmptyException;
    void writeFile(String fileName, String text, boolean recordingMode) throws FileNotCreatedException;
}
