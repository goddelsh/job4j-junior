package ru.job4j.tictactoe;

import java.util.*;

public class ClassicPlayground implements Playground {

    final private IOController ioController;

    private int winnerScore;

    private List<Player> players;

    private int[][] playground;

    private Queue<Player> playersQueue;

    private Player currentPlayer;

    private int freesteps;

    GameStatus currentGameStatus;


    public ClassicPlayground(IOController ioController) {
        this.ioController = ioController;
    }

    @Override
    public void initSettings(int[][] playground, int winnerScore, List<Player> sortedPlayers) {
        this.playground = playground;
        this.freesteps = playground.length * playground[0].length;
        this.winnerScore = winnerScore;
        this.players = sortedPlayers;
        playersQueue = new LinkedList<>(sortedPlayers);
        currentPlayer = null;
        currentGameStatus = GameStatus.START;
    }

    @Override
    public void initSettings(int playgroundSize, int winnerScore, List<Player> sortedPlayers) {
        this.playground = new int[playgroundSize][playgroundSize];
        this.freesteps = playgroundSize * playgroundSize;
        this.winnerScore = winnerScore;
        this.players = sortedPlayers;
        playersQueue = new LinkedList<>(sortedPlayers);
    }

    @Override
    public GameStatus nextPlayerStep() {
        GameStatus result = GameStatus.REPEAT;
        currentPlayer = playersQueue.peek();
        if (this.currentGameStatus == GameStatus.END) {
            throw new IllegalStateException("Game is already ended!");
        } else if (this.freesteps > 0) {
            if (currentPlayer != null) {
                this.ioController.printText("Ход: " + currentPlayer.getName());
                var stepMap = currentPlayer.makeStep(this.playground);
                int x = stepMap.get(Coordinate.X) - 1;
                int y = stepMap.get(Coordinate.Y) - 1;

                if (validateStep(x, y)) {
                    this.playground[x][y] = this.currentPlayer.getOrder();
                    playersQueue.poll();
                    this.freesteps--;
                    result = checkStep(x, y);
                } else {
                    this.ioController.printText("Неверный ход!");
                }
            } else {
                playersQueue.addAll(this.players);
            }
        } else {
           this.currentPlayer = null;
           result = GameStatus.END;
        }
        this.currentGameStatus = result;
        return result;
    }

    @Override
    public Player getWinner() {
        this.ioController.printPlaygroundMatrix(this.playground);
        if (this.currentPlayer != null) {
            this.ioController.printText("Победитель " + this.currentPlayer.getName());
        } else {
            this.ioController.printText("Ничья");
        }
        return this.currentPlayer;
    }

    public boolean validateStep(int x, int y) {
        return  x >= 0 && y >= 0 && x < this.playground.length && y < this.playground.length && this.playground[x][y] == 0;
    }


    public GameStatus checkStep(int x, int y) {
        GameStatus result = GameStatus.CONTINUE;
        int xrate = 1;
        for (int i = x + 1; i < this.playground.length; i++) {
            if (this.playground[i][y] == this.currentPlayer.getOrder()) {
                xrate++;
            } else {
                break;
            }
        }
        for (int i = x - 1; i >= 0; i--) {
            if (this.playground[i][y] == this.currentPlayer.getOrder()) {
                xrate++;
            } else {
                break;
            }
        }

        int yrate = 1;
        for (int i = y + 1; i < this.playground.length; i++) {
            if (this.playground[x][i] == this.currentPlayer.getOrder()) {
                yrate++;
            } else {
                break;
            }
        }
        for (int i = y - 1; i >= 0; i--) {
            if (this.playground[x][i] == this.currentPlayer.getOrder()) {
                yrate++;
            } else {
                break;
            }
        }


        int diagonal = 1;
        int revDiagonal = 1;
        if (x == y || y == playground.length - x - 1 || x == playground.length - y - 1) {
            for (int i = x + 1; i < playground.length; i++) {
                if (this.playground[i][i] == this.currentPlayer.getOrder()) {
                    diagonal++;
                } else {
                    break;
                }
            }
            for (int i = x - 1; i >= 0; i--) {
                if (this.playground[i][i] == this.currentPlayer.getOrder()) {
                    diagonal++;
                } else {
                    break;
                }
            }
            for (int i = x + 1; i < playground.length; i++) {
                if (y - i + 1 > 0 && this.playground[i][playground.length - y - i + 1] == this.currentPlayer.getOrder()) {
                    revDiagonal++;
                } else {
                    break;
                }
            }
            for (int i = x - 1; i >= 0; i--) {
                if (this.playground[i][playground.length - i - 1] == this.currentPlayer.getOrder()) {
                    revDiagonal++;
                } else {
                    break;
                }
            }
        }

        if (xrate == this.winnerScore || yrate == this.winnerScore || diagonal == this.winnerScore || revDiagonal == this.winnerScore) {
            result = GameStatus.END;
        }
        return result;
    }

}
