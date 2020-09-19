package ru.job4j.tictactoe;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ClassicPlaygroundTest {
    IOController ioController = new IOController() {

        @Override
        public int getIntVale(String question) {
            return 0;
        }

        @Override
        public String getTextValue(String question) {
            return null;
        }

        @Override
        public void printText(String text) {

        }

        @Override
        public void printPlaygroundMatrix(int[][] matrix) {

        }
    };

    class TestPlayer implements Player {

        private int order;

        private int x;
        private int y;

        final private  String name;

        TestPlayer(String name) {
            this.name = name;
        }


        void setNextStepCoordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public Map<Coordinate, Integer> makeStep(int[][] playgroundMatrix) {
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
    };


    @Test
    public void testHorizantalWin() {
        var playground = new ClassicPlayground(ioController);
        var firstPlayer = new TestPlayer("Tom");
        firstPlayer.setOrder(1);
        var secondPlayer = new TestPlayer("Bob");
        secondPlayer.setOrder(2);

        playground.initSettings(new int[][]{{1, 1, 0}, {0, 1, 2}, {2, 2, 0}}, 3, List.of(firstPlayer, secondPlayer));
        firstPlayer.setNextStepCoordinates(1, 3);
        assertThat(playground.nextPlayerStep(), is(GameStatus.END));
        assertThat(playground.getWinner().getName(), is("Tom"));
        playground.initSettings(new int[][]{{1, 1, 0}, {0, 1, 2}, {2, 2, 0}}, 3, List.of(firstPlayer, secondPlayer));
        firstPlayer.setNextStepCoordinates(2, 1);
        secondPlayer.setNextStepCoordinates(3, 3);
        assertThat(playground.nextPlayerStep(), is(GameStatus.CONTINUE));
        assertThat(playground.nextPlayerStep(), is(GameStatus.END));
        assertThat(playground.getWinner().getName(), is("Bob"));
    }


    @Test
    public void testVerticalWin() {
        var playground = new ClassicPlayground(ioController);
        var firstPlayer = new TestPlayer("Tom");
        firstPlayer.setOrder(1);
        var secondPlayer = new TestPlayer("Bob");
        secondPlayer.setOrder(2);

        playground.initSettings(new int[][]{{1, 1, 0}, {0, 1, 2}, {2, 0, 0}}, 3, List.of(firstPlayer, secondPlayer));
        firstPlayer.setNextStepCoordinates(3, 2);
        assertThat(playground.nextPlayerStep(), is(GameStatus.END));
        assertThat(playground.getWinner().getName(), is("Tom"));
        playground.initSettings(new int[][]{{1, 0, 2}, {0, 1, 2}, {2, 1, 0}}, 3, List.of(firstPlayer, secondPlayer));
        firstPlayer.setNextStepCoordinates(2, 1);
        secondPlayer.setNextStepCoordinates(3, 3);
        assertThat(playground.nextPlayerStep(), is(GameStatus.CONTINUE));
        assertThat(playground.nextPlayerStep(), is(GameStatus.END));
        assertThat(playground.getWinner().getName(), is("Bob"));
    }

    @Test
    public void testDiagonalWin() {
        var playground = new ClassicPlayground(ioController);
        var firstPlayer = new TestPlayer("Tom");
        firstPlayer.setOrder(1);
        var secondPlayer = new TestPlayer("Bob");
        secondPlayer.setOrder(2);

        playground.initSettings(new int[][]{{1, 0, 2}, {0, 1, 2}, {2, 0, 0}}, 3, List.of(firstPlayer, secondPlayer));
        firstPlayer.setNextStepCoordinates(3, 3);
        assertThat(playground.nextPlayerStep(), is(GameStatus.END));
        assertThat(playground.getWinner().getName(), is("Tom"));
        playground.initSettings(new int[][]{{1, 0, 2}, {0, 2, 1}, {0, 1, 1}}, 3, List.of(firstPlayer, secondPlayer));
        firstPlayer.setNextStepCoordinates(1, 2);
        secondPlayer.setNextStepCoordinates(3, 1);
        assertThat(playground.nextPlayerStep(), is(GameStatus.CONTINUE));
        assertThat(playground.nextPlayerStep(), is(GameStatus.END));
        assertThat(playground.getWinner().getName(), is("Bob"));
    }

}