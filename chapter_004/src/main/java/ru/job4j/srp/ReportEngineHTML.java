package ru.job4j.srp;

import java.text.SimpleDateFormat;
import java.util.function.Predicate;

public class ReportEngineHTML implements Report {
    final private Store store;

    public ReportEngineHTML(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String result = null;
        StringBuilder text = new StringBuilder();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        text.append("<tr><th>Name</th><th>Hired</th><th>Fired</th><th>Salary</th></tr>");
        for (Employee employee : store.findBy(filter)) {
            text.append("<tr>");
            text.append("<th>").append(employee.getName()).append("</th><th>")
                    .append(format.format(employee.getHired().getTime())).append("</th><th>")
                    .append(format.format(employee.getFired().getTime())).append("</th><th>")
                    .append(employee.getSalary()).append("</th>");
            text.append("</tr>");
        }
        result = String.format("<html><head></head><body><table>%s</table></body></html>", text.toString());
        return result;
    }
}
