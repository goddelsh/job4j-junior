package ru.job4j.tdd;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.Ignore;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class
CinemaTest {

    @Ignore
    @Test
    public void buy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, new Session3D(date));
        assertThat(ticket, is(new Ticket3D()));
    }

    @Ignore
    @Test
    public void findTicket() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        var session = new Session3D(date);
        Ticket ticket = cinema.buy(account, 1, 1, session);
        assertThat(cinema.findTicket(account, 1, 1, session), is(ticket));
    }

    @Ignore
    @Test
    public void checkTicket() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        var session = new Session3D(date);
        cinema.add(session);
        Ticket ticket = cinema.buy(account, 1, 1, session);
        assertThat(ticket.getSession(), is(session));
    }

    @Ignore
    @Test
    public void refund() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        var session = new Session3D(date);
        Ticket ticket = cinema.buy(account, 1, 1, session);
        assertThat(cinema.refund(ticket), is(true));
        assertThat(cinema.findTicket(account, 1, 1, session), is(IsNull.nullValue()));
    }

    @Ignore
    @Test
    public void find() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D(Calendar.getInstance()));
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(new Session3D(Calendar.getInstance()))));
    }

}