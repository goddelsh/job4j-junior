package ru.job4j.tictactoe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClassicSettings implements Settings {

    final private int classicSize;

    final private int classicWinScore;

    final private IOController ioController;

    final private List<Player> players;

    public ClassicSettings(int classicSize, int classicWinScore, IOController ioController, List<Player> players) {
        this.classicSize = classicSize;
        this.classicWinScore = classicWinScore;
        this.ioController = ioController;
        this.players = new ArrayList<>(players);
    }

    @Override
    public int getPlaygroundSize() {
        return classicSize;
    }

    @Override
    public int getScoreForWin() {
        return classicWinScore;
    }

    @Override
    public List<Player> gerSortedPlayers() {
        int firstPlayerOrder = 0;
        do {
            this.ioController.printText("Список игроков:");
            for (int i = 0; i < players.size(); i++) {
                this.players.get(i).setOrder(i + 1);
                this.ioController.printText(String.format("%d. %s", this.players.get(i).getOrder(), this.players.get(i).getName()));
            }
            firstPlayerOrder = this.ioController.getIntVale("ВЫбери номер игрока, ходящего первым:");
        } while (0 >= firstPlayerOrder || firstPlayerOrder > players.size());
        return this.sortPlayers(firstPlayerOrder);
    }

    private List<Player> sortPlayers(int firstPlayerOrder) {
        this.players.get(0).setOrder(this.players.get(firstPlayerOrder - 1).getOrder());
        this.players.get(firstPlayerOrder - 1).setOrder(1);
        Collections.swap(this.players, firstPlayerOrder - 1, 0);
        return this.players;
    }
}
