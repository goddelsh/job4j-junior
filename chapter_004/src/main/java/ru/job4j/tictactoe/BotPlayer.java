package ru.job4j.tictactoe;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class BotPlayer implements Player {

    final private IOController ioController;

    private int order;

    final private String name;

    public BotPlayer(IOController ioController, String name) {
        this.ioController = ioController;
        this.name = name;
    }


    @Override
    public Map<Coordinate, Integer> makeStep(int[][] playgroundMatrix) {
        this.ioController.printPlaygroundMatrix(playgroundMatrix);
        var freePointSrray = new ArrayList<Map<Coordinate, Integer>>();
        for (int i = 0; i < playgroundMatrix.length; i++) {
            for (int j = 0; j < playgroundMatrix[i].length; j++) {
                if (playgroundMatrix[i][j] == 0) {
                    freePointSrray.add(Map.of(Coordinate.X, i + 1, Coordinate.Y, j + 1));
                }
            }
        }
        Random rand = new Random();
        int index = rand.nextInt(freePointSrray.size());
        ioController.printText("Ход: X " + freePointSrray.get(index).get(Coordinate.X)
                + " Y " + freePointSrray.get(index).get(Coordinate.Y));
        return freePointSrray.get(index);
    }

    @Override
    public int getOrder() {
        return this.order;
    }

    @Override
    public void setOrder(int order) {
            this.order = order;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
