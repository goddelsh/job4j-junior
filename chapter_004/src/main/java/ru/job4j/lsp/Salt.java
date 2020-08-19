package ru.job4j.lsp;

import java.util.Calendar;

public class Salt extends Food {
    public Salt(String name, Calendar createDate, double price, double disscount) {
        var expireDate = (Calendar) createDate.clone();
        expireDate.add(Calendar.DATE, 100);
        setName(name);
        setCreateDate(createDate);
        setExpireDate(expireDate);
        setPrice(price);
        setDisscount(disscount);
    }
}
