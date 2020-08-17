package ru.job4j.srp;

import java.util.Comparator;
import java.util.function.Predicate;

public class ReportEngineHR implements Report {
    final private Store store;

    public ReportEngineHR(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;").append(System.lineSeparator());
        var empls = store.findBy(filter);
        empls.sort((Employee o1, Employee o2) -> Double.compare(o2.getSalary(), o1.getSalary()));
        for (Employee employee : empls) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";").append(System.lineSeparator());
        }
        return text.toString();
    }
}
