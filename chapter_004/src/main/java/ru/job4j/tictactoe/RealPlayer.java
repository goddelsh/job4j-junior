package ru.job4j.tictactoe;

import java.util.HashMap;
import java.util.Map;

public class RealPlayer implements Player {

    final private IOController ioController;

    private int order;

    final private String name;

    public RealPlayer(IOController ioController, String name) {
        this.ioController = ioController;
        this.name = name;
    }

    @Override
    public Map<Coordinate, Integer> makeStep(int[][] playgroundMatrix) {
        this.ioController.printPlaygroundMatrix(playgroundMatrix);
        int x = this.ioController.getIntVale("Выберите X: ");
        int y = this.ioController.getIntVale("Выберите Y: ");
        return Map.of(Coordinate.X, x, Coordinate.Y, y);
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
