package ru.job4j.tdd;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class Cinema3D implements Cinema {

    @Override
    public Ticket buy(Account account, int row, int col, Session session) {
        return null;
    }

    @Override
    public boolean refund(Ticket ticket) {
        return false;
    }

    @Override
    public void add(Session session) {

    }

    @Override
    public List<Session> find(Predicate<Boolean> predicate) {
        return null;
    }

    @Override
    public Ticket findTicket(Account account, int row, int col, Session session) {
        return null;
    }

}
