package ru.job4j.lsp;

import junit.framework.TestCase;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Calendar;
import java.util.List;

public class ControllQualityTest extends TestCase {

    public void testPlaceFood() {
        final var placeList = List.of(new Warehouse(), new Shop(), new Trash());

        var calMinusFour = Calendar.getInstance();
        calMinusFour.add(Calendar.DATE, -4);

        var calMinusTwenty = Calendar.getInstance();
        calMinusTwenty.add(Calendar.DATE, -20);

        var bread = new Bread("Bread", calMinusFour, 100, 0.8);
        var breadOld = new Bread("Bread Old", calMinusTwenty, 100, 0.8);
        var oilAtTheEnd = new Oil("Oil no fresh", calMinusTwenty, 200, 0.8);
        var salt = new Salt("Salt", calMinusFour, 200, 0.8);

        new ControllQuality(placeList).placeFood(List.of(bread, breadOld, oilAtTheEnd, salt));

        var warhouseFoodList = placeList.get(0).getFood();
        var storeFoodList = placeList.get(1).getFood();
        var trashFoodList = placeList.get(2).getFood();

        assertThat(storeFoodList.size(), is(2));
        assertThat(warhouseFoodList.size(), is(1));
        assertThat(trashFoodList.size(), is(1));

        assertThat(storeFoodList.contains(bread), is(true));
        assertThat(storeFoodList.contains(oilAtTheEnd), is(true));
        assertThat(oilAtTheEnd.getPrice(), is(160.0));
        assertThat(warhouseFoodList.contains(salt), is(true));
        assertThat(trashFoodList.contains(breadOld), is(true));

    }

    public void testResort() {
        final var placeList = List.of(new Warehouse(), new Shop(), new Trash());

        var calMinusFour = Calendar.getInstance();
        calMinusFour.add(Calendar.DATE, -4);

        var calMinusTwenty = Calendar.getInstance();
        calMinusTwenty.add(Calendar.DATE, -20);

        var bread = new Bread("Bread", calMinusFour, 100, 0.8);
        var breadOld = new Bread("Bread Old", calMinusTwenty, 100, 0.8);
        var oilAtTheEnd = new Oil("Oil no fresh", calMinusTwenty, 200, 0.8);
        var salt = new Salt("Salt", calMinusFour, 200, 0.8);

        var control = new ControllQuality(placeList);
        control.placeFood(List.of(bread, breadOld, oilAtTheEnd, salt));

        var storeFoodList = placeList.get(1).getFood();
        assertThat(storeFoodList.contains(bread), is(true));

        bread.setExpireDate(Calendar.getInstance());
        control.resort();

        storeFoodList = placeList.get(1).getFood();
        assertThat(storeFoodList.contains(bread), is(false));

        var trashFoodList = placeList.get(2).getFood();
        assertThat(trashFoodList.contains(bread), is(true));
    }
}