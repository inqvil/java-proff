package ru.otus.app;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ioc {
    private Ioc() {}

    public static Calculate createCalculateLogImpl(Calculate calculate){
        InvocationHandler invocationHandler = new MyHandler(calculate);
        return (Calculate) Proxy.newProxyInstance(Ioc.class.getClassLoader(), new Class<?>[]{Calculate.class}, invocationHandler);

    }
    private static class MyHandler implements InvocationHandler {
        private final Calculate calculate;
        private final List<Method> methodsWithAnnotation;

        MyHandler(Calculate calculate) {
            this.calculate = calculate;
            List<Method> methodsWithAnnotationCalculate = new ArrayList<>();

            for (Method method : calculate.getClass().getDeclaredMethods()) {
                for (Annotation annotation : method.getDeclaredAnnotations()) {
                    if (annotation.annotationType() == Log.class) {
                        methodsWithAnnotationCalculate.add(method);
                    }
                }
            }
            this.methodsWithAnnotation = methodsWithAnnotationCalculate;
        }


        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //проверяем по названию и по массиву аннотаций
            for (Method annotatedMethod: methodsWithAnnotation) {
                if (annotatedMethod.getName().equals(method.getName()) &&
                        Arrays.toString(annotatedMethod.getParameterTypes()).equals(Arrays.toString(method.getParameterTypes()))) {
                    System.out.println("выполняем метод " + method.getName() + ", параметры " + Arrays.toString(args));
                    break;
                }
            }
            return method.invoke(calculate, args);
        }

    }
}