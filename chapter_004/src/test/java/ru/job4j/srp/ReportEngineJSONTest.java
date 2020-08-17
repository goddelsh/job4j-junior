package ru.job4j.srp;

import com.google.gson.Gson;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ReportEngineJSONTest {

    @Test
    public void generateReportInJSON() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngineJSON(store);

        assertThat(engine.generate(em -> true), is(new Gson().toJson(List.of(worker))));
    }
}