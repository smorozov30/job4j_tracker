package ru.job4j.newtracker.di;

import java.util.Scanner;

public class ConsoleInput {
    public String input() {
        return "From ConsoleInput: " + new Scanner(System.in).nextLine();
    }
}
