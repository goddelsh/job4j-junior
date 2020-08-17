package ru.job4j.srp;

import com.google.gson.Gson;

import java.util.function.Predicate;

public class ReportEngineJSON implements Report {
    final private Store store;

    public ReportEngineJSON(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return new Gson().toJson(store.findBy(filter));
    }
}
