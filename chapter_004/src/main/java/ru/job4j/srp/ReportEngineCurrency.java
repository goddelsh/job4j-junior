package ru.job4j.srp;

import java.util.function.Predicate;

public class ReportEngineCurrency implements Report {
    final private Store store;
    final private Double currency;

    public ReportEngineCurrency(Store store, Double currency) {
        this.store = store;
        this.currency = currency;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;").append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary() * currency).append(";").append(System.lineSeparator());
        }
        return text.toString();
    }
}
