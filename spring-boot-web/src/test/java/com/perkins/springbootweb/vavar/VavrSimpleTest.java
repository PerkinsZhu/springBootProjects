package com.perkins.springbootweb.vavar;

import io.vavr.*;
import io.vavr.collection.List;
import io.vavr.collection.Stream;
import io.vavr.control.Try;
import lombok.extern.java.Log;
import org.apache.tomcat.util.buf.StringUtils;
import org.junit.Test;
import org.omg.SendingContext.RunTime;

import java.util.*;
import java.util.function.UnaryOperator;

import static io.vavr.API.*;
import static io.vavr.Patterns.$Failure;
import static io.vavr.Patterns.$Success;

/**
 * @author ：Perkins Zhu
 * @date ：Created in 2020/6/20 15:23
 * @description：
 * @modified By：
 * @version: 1.0
 */
@Log
public class VavrSimpleTest {

    @Test
    public void testTry() {
        /*Try<Integer> result = divide(10, 0);
        print(result.get());

        List<String> list = Collections.unmodifiableList(new ArrayList<>(1));
        list.add("000");*/

        List<Integer> list1 = List.of(1, 2, 3);
        Stream.of(1, 2, 3).map(Object::toString).forEach(a -> print(a));

        // (Java, 8)
        Tuple2<String, Integer> java8 = Tuple.of("Java", 8);

        // "Java"
        String s = java8._1;

        // 8
        Integer i = java8._2;
// (vavr, 1)
        Tuple2<String, Integer> that = java8.map(s1 -> s1.substring(2) + "vr", i1 -> i1 / 8);
        print(that);

      /*  Tuple2<String, Integer> that = java8.map(
                (s, i) -> Tuple.of(s.substring(2) + "vr", i / 8)
        );*/

        Function3<Integer, Integer, Integer, Integer> sum = (a, b, c) -> a + b + c;
        final Function1<Integer, Function1<Integer, Integer>> add2 = sum.curried().apply(2);
//        then(add2.apply(4).apply(3)).isEqualTo(9);
        Lazy<Double> lazy = Lazy.of(Math::random);
        lazy.isEvaluated(); // = false
        lazy.get();         // = 0.123 (random generated)
        lazy.isEvaluated(); // = true
        lazy.get();         // = 0.123 (memoized)
        for (double random : Stream.continually(Math::random).take(1000)) {
//            print(random);
        }

        Optional a = Arrays.asList(1, 2, 3).stream().reduce((ii, j) -> ii + j);
        print(a.get());

    }

    Try<Integer> divide(Integer dividend, Integer divisor) {
        return Try.of(() -> dividend / divisor);
    }


    private void print(Object o) {
        System.out.println(o);
    }


    @Test
    public void testStream() {
        Stream.from(1).filter(i -> i % 2 == 0).forEach(a -> print(a));
    }

    @Test
    public void testMatch() {
        int i = 1;
        String s = Match(i).of(
                Case($(1), "one"),
                Case($(2), "two"),
                Case($(), "?")
        );
        print(s);
        Try _try = divide(1, 2);
    }

    @Test
    public void test() {
        /*java.util.List<String> otherList = new ArrayList<>();
        java.util.List<String> list = Collections.unmodifiableList(otherList);
        *//**
         * 这里会抛出 UnsupportedOperationException
         *  Collections.unmodifiableListN 返回的是
         *  java.util.Collections.UnmodifiableList#UnmodifiableList
         *//*
        list.add("a");*/
        //指定元素数量
        List list1 =List.of(1,2,3,4);
        //循环10次填充1
        List list2 =List.fill(10,1);
        //填充1-> 100 的数据
        List list3 = List.range(1,100);
        //指定步长为20
        List list4 = List.rangeBy(1,100,20);
        // vavr List 转换为 java list
        java.util.List<String> list5 = list4.asJava();
//        list5.add("A"); //不可变list，无法添加元素
        //转变为可变list,
        java.util.List<String> list6 = list4.asJavaMutable();
        list6.add("A");
        showList(list1);
        showList(list2);
        showList(list3);
        showList(list4);

    }

    private void showList(List list) {
//        print(StringUtils.join(list.asJava(), '、'));
        list.forEach(i ->{print(i);});
    }


}
