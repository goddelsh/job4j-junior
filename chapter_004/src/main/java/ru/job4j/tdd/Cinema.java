package ru.job4j.tdd;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public interface Cinema {
    Ticket buy(Account account, int row, int col, Session session);

    boolean refund(Ticket ticket);

    void add(Session session);

    List<Session> find(Predicate<Boolean> predicate);

    Ticket findTicket(Account account, int row, int col, Session session);
}
