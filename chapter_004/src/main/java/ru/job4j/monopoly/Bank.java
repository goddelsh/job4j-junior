package ru.job4j.monopoly;

public interface Bank {
    boolean giveMoney(Player player, Long amount);
    boolean takeMoney(Player player, Long amount);
    Long getAllBank();
}
