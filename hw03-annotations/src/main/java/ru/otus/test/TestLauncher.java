package ru.otus.test;

import ru.otus.annotation.After;
import ru.otus.annotation.Before;
import ru.otus.annotation.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class TestLauncher {
    public static void launchTestClass(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            Method[] methods = clazz.getDeclaredMethods();

            List<Method> beforeMethodList = new ArrayList<>();
            List<Method> afterMethodList = new ArrayList<>();
            List<Method> testsMethodList = new ArrayList<>();

            for (Method method : methods) {
                Annotation[] annotations = method.getDeclaredAnnotations();
                for (Annotation annotation : annotations) {
                    Class<?> annotationType = annotation.annotationType();

                    if (annotationType == Before.class) {
                        beforeMethodList.add(method);
                    }
                    else if (annotationType == After.class) {
                        afterMethodList.add(method);
                    } else if (annotationType == Test.class){
                        testsMethodList.add(method);
                    }
                }
            }
            Constructor<?> constructor = clazz.getConstructor();


            int count = 0;
            for (Method method : testsMethodList) {
                boolean isTestSuccess = launchTest(constructor, method, beforeMethodList, afterMethodList);
                if (isTestSuccess) {
                    count++;
                }
            }
            System.out.println("Всего тестов: " + testsMethodList.size() + "\nПройдено: " + count);
        } catch (ClassNotFoundException | NoSuchElementException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean launchTest(Constructor<?> constructor, Method method, List<Method> beforeMethodList, List<Method> afterMethodList) {
        try {
            Object object = constructor.newInstance();

            boolean beforeSuccess;
            boolean afterSuccess;
            boolean isTestSuccess = false;

            beforeSuccess = launchMethods(beforeMethodList, object);

            if (beforeSuccess) {
                isTestSuccess = launchMethod(method, object);
            }
            afterSuccess = launchMethods(afterMethodList, object);

            return beforeSuccess && afterSuccess && isTestSuccess;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            return false;
        }

    }

    private static boolean launchMethod(Method method, Object object) {
        try {
            method.invoke(object);
            return true;
        } catch (IllegalAccessException | InvocationTargetException e) {
            return false;
        }
    }

    private static boolean launchMethods(List<Method> methodList, Object object) {
        boolean isSuccess = true;

        for (Method method : methodList) {
            if (!launchMethod(method, object)) {
                isSuccess = false;
            }
        }
        return isSuccess;
    }
}
