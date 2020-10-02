package ru.job4j.monopoly;

public interface Player {
    void initPlayerStarterPack(Buildings buildings, Securities securities, Long amount);
    int getStepsCount();
    Player getWinner();
}
