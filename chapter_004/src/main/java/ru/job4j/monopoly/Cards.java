package ru.job4j.monopoly;

import java.util.List;

public interface Cards {
    List<String> getAllCards();
    List<String> getCardProperties(String name);
    boolean getCard(String name);
    boolean useCard(String name);
}
