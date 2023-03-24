package ru.otus;

import ru.otus.app.CalculateImpl;
import ru.otus.app.Ioc;

public class Main {
    public static void main(String[] args) {
        var calculate = Ioc.createCalculateLogImpl(new CalculateImpl());
        calculate.calculation(1323);



    }
}