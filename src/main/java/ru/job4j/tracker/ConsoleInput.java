package ru.job4j.tracker;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String askStr(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    @Override
    public int askInt(String question) {
        return Integer.valueOf(askStr(question));
    }

    @Override
    public int askInt(String question, int max) {
        String valueStr = this.askStr(question);
        hasValid(valueStr, max);
        int select = Integer.valueOf(valueStr);
        return select;
    }

    private boolean hasValid(String valueStr, int max) {
        if (!checkInt(valueStr)) {
            throw new NumberFormatException();
        }
        if (!checkBound(valueStr, max)) {
            throw new IllegalStateException();
        }
        return true;
    }

    private boolean checkInt(String valueStr) {
        Integer.valueOf(valueStr);
        return true;
    }

    private boolean checkBound(String valueStr, int max) {
        int select = Integer.valueOf(valueStr);
        if (select < 0 || select >= max) {
            throw new IllegalStateException(String.format("Out of about %s > [0, %s]", select, max));
        }
        return true;
    }
}
