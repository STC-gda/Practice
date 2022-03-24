package main.java.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Demo {

//    public static class A{
//        public static void methodA(int i){
//            System.out.println("U did it!A");
//        }
//
//    }
//
//    public static class B{
//        public void doSomething(int i, IntConsumer aMethod){
//            System.out.println("U did itB!");
//            aMethod.accept(i);
//
//        }
//    }
//
//    public static class C{
//        B b = new B();
//
//        public C(){
//            b.doSomething(100, j->A.methodA(j));
//            System.out.println("You did nothing C");
//        }
//    }
//
//    public static void main(String[] args) {
//        Map<String, String> map = new HashMap<>();
//        map.put("name", "qiyexue");
//        map.put("swag", "big");
//        BiFunction<String, String, String> biFunction = (k, v) -> k + " : " + map.get(k).toUpperCase();
//        System.out.println(map.compute("name", biFunction));
//        System.out.println(map.compute("swag", biFunction));
//
//
//    }

    public static void main(String[] args) {
        Function<Integer, Integer> function1 = value -> value * 2;
        Function<Integer, Integer> function2 = value -> value + 3;
        Function<Integer, Integer> function3 = function1.compose(function2);
        Function<Integer, Integer> function4 = function1.andThen(function2);
        // function3,先执行function2的apply方法再执行function1的apply方法, 所以结果为(2 + 3) * 2 = 10
        System.out.println("function3 : " + function3.apply(2));
        // function4,先执行function1的apply方法再执行function2的apply方法, 所以结果为2 * 2 + 3 = 7
        System.out.println("function4 : " + function4.apply(2));

        Function<Integer, String> function5 = value -> "Hello World " + value;
        Function<String, List<String>> function6 = value -> {
            return new ArrayList<>(Arrays.asList(value));
        };
        Function<Long, Integer> function7 = value -> value.intValue();
        // function8 是function6调用compose组合function5
        // function6是String->List<String>, function5是Integer->String, 所以组合之后,Function8是Integer->List<String>
        Function<Integer, List<String>> function8 = function6.compose(function5);
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);
        list.stream().map(function8).forEach(element -> System.out.println(element));

        // function9 是function7调用andThen方法组合function5
        // function7是Long->Integer, function5是Integer->String, 所以组合之后,Function9是Long->String
//        Function<Long, String> function9 = function7.andThen(function5);
//        // 输出: [Hello World 1]
//        System.out.println(function8.apply(1));
//        // 输出: Hello World 100
//        System.out.println(function9.apply(100L));
    }



}
