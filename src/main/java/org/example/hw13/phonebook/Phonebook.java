package org.example.hw13.phonebook;

import java.util.ArrayList;
import java.util.List;

public class Phonebook {
    private List<Record> records;

    public Phonebook() {
        records = new ArrayList<>();
    }

    public void add(Record record) {
        records.add(record);
    }

    public List<Record> find(String name) {
        List<Record> result = new ArrayList<>();
        for (Record record : records) {
            if (record.getName().equals(name)) {
                result.add(record);
            }
        }
        return result;
    }

    public List<Record> findAll(String ...names) {
        List<Record> result = new ArrayList<>();
        for (String name : names) {
            result.addAll(find(name));
        }
        return result;
    }
}
