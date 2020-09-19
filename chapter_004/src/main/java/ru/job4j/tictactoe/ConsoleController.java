package ru.job4j.tictactoe;

import java.util.Scanner;

public class ConsoleController implements  IOController {

    final private Scanner scanner = new Scanner(System.in);


    @Override
    public int getIntVale(String question) {
        System.out.println(question);
        return scanner.nextInt();
    }

    @Override
    public String getTextValue(String question) {
        System.out.println(question);
        return scanner.next();
    }

    @Override
    public void printText(String text) {
        System.out.println(text);
    }

    @Override
    public void printPlaygroundMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
         //   System.out.print((i + 1) + ". ");
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    System.out.print("X");
                } else if (matrix[i][j] == 2) {
                    System.out.print("O");
                } else {
                    System.out.print(" ");
                }
                if (j + 1 < matrix[i].length) {
                    System.out.print("|");
                }
            }
            System.out.println();
            System.out.println("-------------");
        }
    }
}
