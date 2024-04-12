package org.example.bd.impl;

import org.example.bd.WorksWithDatabase;
import org.example.model.exception.FileListIsEmptyException;
import org.example.model.exception.FileNotCreatedException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Database implements WorksWithDatabase {
    private static final String DIR = "db_input";
    @Override
    public String readAll(String fileName) throws IOException {
        String path = "%s/%s".formatted(DIR, fileName);
        StringBuilder sb = new StringBuilder();
            File file = new File(path);
            try(
                    FileReader fr = new FileReader(file);
                    BufferedReader reader = new BufferedReader(fr)
            ) {
                String line = reader.readLine();
                if (line != null) {
                    sb.append(line);
                }
                while (line != null) {
                    line = reader.readLine();
                    if (line != null) {
                        sb.append(line);
                    }
                }
            }
        return sb.toString();
    }

    @Override
    public List<String> showListOfFiles() throws FileListIsEmptyException {
        File dir = new File(DIR);
        File[] arrFiles = dir.listFiles();

        if (arrFiles == null || arrFiles.length == 0)
            throw new FileListIsEmptyException("У вас нет файлов для просмотра");

        List<String> nameFiles = new ArrayList<>();
        for (File arrFile : arrFiles) {
            nameFiles.add(arrFile.getName());
        }
        return nameFiles;
    }

    @Override
    public void writeFile(String fileName, String text, boolean recordingMode) throws FileNotCreatedException {
        String path = "%s/%s".formatted(DIR, fileName);
        try (FileWriter fw = new FileWriter(path, recordingMode)){
            fw.write(text + " ");
            fw.flush();
        } catch (IOException e) {
            throw new FileNotCreatedException("Файл не создан");
        }
    }
}