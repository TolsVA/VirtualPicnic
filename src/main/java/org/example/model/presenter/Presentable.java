package org.example.model.presenter;

public interface Presentable {
    void getMapFruits();
    void getFruitCounter();
    void getMaxLengthName();
    String prompt(String message);
    void readFile();
    void writeFile();
    void createFile();
    void setIndex(int index);
}
