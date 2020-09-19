package ru.job4j.tictactoe;

import java.util.List;

public interface Settings {
    int getPlaygroundSize();
    int getScoreForWin();
    List<Player> gerSortedPlayers();
}
