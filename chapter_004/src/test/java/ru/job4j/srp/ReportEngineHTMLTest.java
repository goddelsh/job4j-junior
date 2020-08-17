package ru.job4j.srp;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ReportEngineHTMLTest {

    @Test
    public void testHTML() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngineHTML(store);

        StringBuilder text = new StringBuilder();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        text.append("<tr><th>Name</th><th>Hired</th><th>Fired</th><th>Salary</th></tr>");

            text.append("<tr>");
            text.append("<th>").append(worker.getName()).append("</th><th>")
                    .append(format.format(worker.getHired().getTime())).append("</th><th>")
                    .append(format.format(worker.getFired().getTime())).append("</th><th>")
                    .append(worker.getSalary()).append("</th>");
            text.append("</tr>");

        String expect = String.format("<html><head></head><body><table>%s</table></body></html>", text.toString());

        assertThat(engine.generate(em -> true), is(expect));
    }
}