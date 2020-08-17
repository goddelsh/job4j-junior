package ru.job4j.srp;

import java.util.function.Predicate;

public interface Report {
    public String generate(Predicate<Employee> filter);
}
