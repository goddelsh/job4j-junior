package ru.job4j.tdd;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class Cinema3D implements Cinema {
    @Override
    public Ticket buy(Account account, int i, int i1, Calendar date) {
        return null;
    }

    @Override
    public boolean refund(Ticket ticket) {
        return false;
    }

    @Override
    public void add(Session3D session3D) {

    }

    @Override
    public List<Session> find(Predicate<Boolean> predicate) {
        return null;
    }

    @Override
    public Ticket findTicket(Account account, int i, int i1, Calendar date) {
        return null;
    }
}
