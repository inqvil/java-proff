package ru.otus.test;

import ru.otus.annotation.After;
import ru.otus.annotation.Before;
import ru.otus.annotation.Test;

public class Example {
    @Before
    void beforeTest1(){
        System.out.println("некие действия перед тестом 1");
    }
    @Before
    void beforeTest2(){
        System.out.println("некие действия перед тестом 2");
    }
    @Test
    void test1(){
        int a = Integer.parseInt("obama");
        System.out.println("этот тест не пройдет " + a);
    }
    @Test
    void test2(){
        int a = Integer.parseInt("69");
        System.out.println("этот тест пройдет " + a);
    }
    @After
    void afterTest1(){
        System.out.println("некие действия после 1 теста");
    }
    @After
    void afterTest2(){
        System.out.println("некие действия после 2 теста");
    }

}
