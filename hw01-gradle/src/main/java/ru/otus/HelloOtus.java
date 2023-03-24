package ru.otus;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;

public class HelloOtus {
    public static void main(String[] args) {
        List<String> names = Lists.newArrayList("John", "Adam", "Jane");
        List<String> reversedNames = Lists.reverse(names);
        System.out.println(reversedNames);

    }


}
