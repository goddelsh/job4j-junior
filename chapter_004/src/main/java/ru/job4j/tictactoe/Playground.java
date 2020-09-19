package ru.job4j.tictactoe;

import java.util.List;

public interface Playground {
    /**
     * Инициализация параметров игрового поля
     * @param playgroundSize размер доски
     * @param winnerScore счет для победы
     * @param sortedPlayers отсортированные игроки в порядки очереди
     */
    void initSettings(int playgroundSize, int winnerScore, List<Player> sortedPlayers);

    /**
     * Инициализация параметров игрового поля с уже готовой доской
     * @param playground готовая доска
     * @param winnerScore счет для победы
     * @param sortedPlayers отсортированные игроки в порядки очереди
     */
    void initSettings(int[][] playground, int winnerScore, List<Player> sortedPlayers);

    /**
     * Ход следующего игрока
     * @return состояние игры после хода
     */
    GameStatus nextPlayerStep();

    /**
     * Получить победителя если имеется
     * @return победитель, если null то ничья
     */
    Player getWinner();
}
