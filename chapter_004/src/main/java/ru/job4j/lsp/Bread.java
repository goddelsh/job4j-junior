package ru.job4j.lsp;

import java.util.Calendar;

public class Bread extends Food {
    public Bread(String name, Calendar createDate, double price, double disscount) {
        var expireDate = (Calendar)createDate.clone();
        expireDate.add(Calendar.DAY_OF_MONTH, 7);
        setName(name);
        setCreateDate(createDate);
        setExpireDate(expireDate);
        setPrice(price);
        setDisscount(disscount);
    }

}
