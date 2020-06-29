package com.perkins.springbootservice;

import io.vavr.*;
import io.vavr.collection.List;
import io.vavr.control.Option;
import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

public class SimpleTest {

    @Test
    public void testVavr() {

        //指定元素数量
        List<Integer> list1 =List.of(1,2,3,4);
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
        int a = list1.map(i -> (i * 10)).flatMap(i-> List.fill(i,i)).foldLeft(0,(m,n)->m + n); // 3000


    }




    class A {
    }

    @Test
    public void testTuple() {
        Tuple0 entry0 = Tuple.empty();
        Tuple1<Integer> entry1 = Tuple.of(1);
        Tuple2<Integer, String> entry2 = Tuple.of(1, "A");
        Tuple3<Integer, String, A> entry3 = Tuple.of(1, "A", new A());
        //entry3._1 = 1
        //entry3._2 = "A"
        //entry3._1 = A()
        //通过map实现了类型的转换
        Tuple2<String,Integer> newTuple2 = entry2.map( s -> "s = "+s, i -> (int)(i.charAt(0)));
    }

    @Test
    public void testFuncation(){
       /* Function1<Integer, Integer> plusOne = a -> a + 1;
        Function1<Integer, Integer> multiplyByTwo = a -> a * 2;
        Function1<Integer, Integer> add1AndMultiplyBy2 = plusOne.andThen(multiplyByTwo);
        Integer result  = add1AndMultiplyBy2.apply(2); // result = 6
        Function1<Integer, Integer> add1AndMultiplyBy3 = multiplyByTwo.compose(plusOne);
        add1AndMultiplyBy3.apply(2);
*/
        //三个入参
        Function3<Integer, Integer, Integer, Integer> sum = (a, b, c) -> a + b * c;
        //转换为两个入参，c默认设置为2
        final Function1<Integer, Function1<Integer, Integer>> add2 = sum.curried().apply(2);
        //转换为一个参数，a默认为2. 此时 mulit3 = （a） -> 2 + 3 * a
        final Function1<Integer, Integer> mulit3 = add2.curried().apply(3);
        Integer result = mulit3.apply(5); //17

        System.out.printf(result+"");

    }


    @Test
    public void testMem(){
       /* Function0<Double> hashCache = Function0.of(Math::random).memoized();
        double randomValue1 = hashCache.apply(); //首次触发生成 hashCache
        double randomValue2 = hashCache.apply(); //第二次调用时返回第一次生成的 hashCache
        System.out.printf(String.valueOf(randomValue1 - randomValue2)); // 0.0
*/
        //java 的 Optional
        Optional<String> javaMaybeFoo = Optional.of("foo");
        Optional<String> javaMaybeFooBar = javaMaybeFoo.map(s -> (String)null)
                .map(s -> s.toUpperCase() + "bar");
        System.out.println(javaMaybeFooBar); //Optional.empty

        //vavr 同样支持 Option,这里的Option和Scala的Option几乎一模一样
        Option<String> maybeFoo = Option.of("foo");
        Option<String> maybeFooBar = maybeFoo.flatMap(s -> Option.of((String)null))
                .map(s -> s.toUpperCase() + "bar");
        System.out.println(maybeFooBar); //None
    }


}
