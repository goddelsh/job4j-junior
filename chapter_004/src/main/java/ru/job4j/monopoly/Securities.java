package ru.job4j.monopoly;

import java.util.List;

public interface Securities {
    List<String> getPlayerSecurities(Player player);
    List<String> getAllSecurities();
    Player checkSecuritiesOwner(String name);
    List<String> getSecuritieProperties(String name);
    Long getSecuritiePrice(String name);
    boolean buySecuritie(Player player, String name);
}
