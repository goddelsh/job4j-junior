package ru.job4j.srp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;

import java.util.function.Predicate;

public class ReportEngineXML implements Report  {
    final private Store store;

    public ReportEngineXML(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String result = null;
        try {
            result = new XmlMapper().writeValueAsString(store.findBy(filter));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
