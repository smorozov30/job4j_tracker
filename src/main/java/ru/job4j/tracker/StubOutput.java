package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

public class StubOutput implements Output {

    private List<String> list = new ArrayList<>();

    @Override
    public void output(String s) {
        list.add(s);
    }

    @Override
    public List<String> getOutput() {
        return list;
    }
}
