package ru.job4j.monopoly;

import java.util.List;

public interface Buildings {
    List<String> getPlayerBuildings(Player player);
    List<String> getAllBuildings();
    Player checkBuildingsOwner(String name);
    List<String> getBuildingProperties(String name);
    Long getBuildPrice(String name);
    boolean buyBuilding(Player player, String name);
    boolean sellBuilding(Player oldOwner, Player newOwner, String name);
}
