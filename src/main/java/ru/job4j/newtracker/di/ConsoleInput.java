package ru.job4j.newtracker.di;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleInput {
    public String input() {
        return "From ConsoleInput: " + new Scanner(System.in).nextLine();
    }
}
