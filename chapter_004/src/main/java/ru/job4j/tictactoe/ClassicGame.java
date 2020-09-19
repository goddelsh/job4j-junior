package ru.job4j.tictactoe;

import java.util.List;

public class ClassicGame {

    final private Settings settings;
    final private Playground playground;

    public ClassicGame(Settings settings, Playground playground) {
        this.settings = settings;
        this.playground = playground;
    }

    public void start() {
        this.playground.initSettings(this.settings.getPlaygroundSize(),
                this.settings.getScoreForWin(),
                this.settings.gerSortedPlayers());
        while (this.playground.nextPlayerStep() != GameStatus.END) {
            System.out.println("======================================");
        }

        this.playground.getWinner();
    }



    public static void main(String[] args) {
        IOController ioController = new ConsoleController();
        Settings settings = new ClassicSettings(3, 3, ioController, List.of(new RealPlayer(ioController, "Bob"),
                new BotPlayer(ioController, "Tom")));
        Playground playground = new ClassicPlayground(ioController);

        new ClassicGame(settings, playground).start();
    }
}
