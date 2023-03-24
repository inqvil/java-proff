package ru.otus.app;

public class CalculateImpl implements Calculate {
    @Log
    @Override
    public void calculation(int number) {
        System.out.println(number + number);
    }
}