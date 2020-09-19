package ru.job4j.tictactoe;

public interface IOController {
    int getIntVale(String question);
    String getTextValue(String question);
    void printText(String text);
    void printPlaygroundMatrix(int[][] matrix);
}
