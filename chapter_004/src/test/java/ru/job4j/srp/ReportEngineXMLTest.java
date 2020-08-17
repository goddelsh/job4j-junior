package ru.job4j.srp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ReportEngineXMLTest {

    @Test
    public void generateReportInXML() throws JsonProcessingException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngineXML(store);
        var list = new ArrayList<Employee>();
        list.add(worker);


        assertThat(engine.generate(em -> true), is(new XmlMapper().writeValueAsString(list)));
    }
}