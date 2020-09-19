package ru.job4j.tictactoe;

import java.util.Map;

public interface Player {
    /**
     * Сделать ход
     * @param playgroundMatrix игровое поле
     * @return данные о ходе
     */
    Map<Coordinate, Integer> makeStep(int[][] playgroundMatrix);

    /**
     * Получение порядкового номера
     * @return
     */
    int getOrder();

    /**
     * Установка порядкового номера
     * @param order
     */
    void setOrder(int order);

    /**
     * Получение имени игрока
     * @return
     */
    String getName();
}
