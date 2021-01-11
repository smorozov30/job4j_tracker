package ru.job4j.newtracker.io.output;

import java.util.List;

public interface Output {
    void output(String s);
    List<String> getOutput();
}
