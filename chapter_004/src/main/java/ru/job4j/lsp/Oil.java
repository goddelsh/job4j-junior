package ru.job4j.lsp;

import java.util.Calendar;

public class Oil extends Food {
    public Oil(String name, Calendar createDate, double price, double disscount) {
        var expireDate = (Calendar) createDate.clone();
        expireDate.add(Calendar.DATE, 22);
        setName(name);
        setCreateDate(createDate);
        setExpireDate(expireDate);
        setPrice(price);
        setDisscount(disscount);
    }
}
