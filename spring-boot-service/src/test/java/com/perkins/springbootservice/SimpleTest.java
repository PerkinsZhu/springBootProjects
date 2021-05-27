package com.perkins.springbootservice;

import com.google.common.util.concurrent.AtomicDouble;
import io.vavr.*;
import io.vavr.collection.List;
import io.vavr.concurrent.Future;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.val;
import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import org.junit.Test;

import static io.vavr.Patterns.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static io.vavr.Patterns.*;
import static io.vavr.Predicates.*;

import static io.vavr.API.*;

public class SimpleTest {

    @Test
    public void testVavr() {

        //指定元素数量
        List<Integer> list1 = List.of(1, 2, 3, 4);
        //循环10次填充1
        List list2 = List.fill(10, 1);
        //填充1-> 100 的数据
        List list3 = List.range(1, 100);
        //指定步长为20
        List list4 = List.rangeBy(1, 100, 20);
        // vavr List 转换为 java list
        java.util.List<String> list5 = list4.asJava();
//        list5.add("A"); //不可变list，无法添加元素
        //转变为可变list,
        java.util.List<String> list6 = list4.asJavaMutable();
        list6.add("A");
        int a = list1.map(i -> (i * 10)).flatMap(i -> List.fill(i, i)).foldLeft(0, (m, n) -> m + n); // 3000


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
        Tuple2<String, Integer> newTuple2 = entry2.map(s -> "s = " + s, i -> (int) (i.charAt(0)));
    }

    @Test
    public void testFuncation() {
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

        System.out.printf(result + "");

    }


    @Test
    public void testMem() {
       /* Function0<Double> hashCache = Function0.of(Math::random).memoized();
        double randomValue1 = hashCache.apply(); //首次触发生成 hashCache
        double randomValue2 = hashCache.apply(); //第二次调用时返回第一次生成的 hashCache
        System.out.printf(String.valueOf(randomValue1 - randomValue2)); // 0.0
*/
        //java 的 Optional
        Optional<String> javaMaybeFoo = Optional.of("foo");
        Optional<String> javaMaybeFooBar = javaMaybeFoo.map(s -> (String) null)
                .map(s -> s.toUpperCase() + "bar");
        System.out.println(javaMaybeFooBar); //Optional.empty

        //vavr 同样支持 Option,这里的Option和Scala的Option几乎一模一样
        Option<String> maybeFoo = Option.of("foo");
        Option<String> maybeFooBar = maybeFoo.flatMap(s -> Option.of((String) null))
                .map(s -> s.toUpperCase() + "bar");
        System.out.println(maybeFooBar); //None
    }


    @Test
    public void testTry() {

        Try result = Try.of(() -> 0)
                .map((a) -> 10 / a) //即使此处抛出异常，不会导致当前线程结束。这里无需使用 try{}catch()对代码进行捕获
                .andThen(() -> System.out.printf("--抛出异常此处不会执行--")) //执行一个动作，不修改结果
                .map(i -> {
                    System.out.println("当前值：" + i);
                    return i + 10;
                })
                .onFailure(e -> e.printStackTrace())//失败时会触发onFailure
                .recover(ArithmeticException.class, 1000) //如果遇到 Exception类型的异常,则返回1000
                .map((a) -> a + 1);

        System.out.println("是否抛出异常：" + result.isFailure());
        System.out.println("执行结果:" + result.getOrElse(100)); //如果有异常发生，则返回100


    }

    @Test
    public void tetLazy() {
        Lazy<Double> lazy = Lazy.of(Math::random)
                .map(i -> {
                    System.out.println("-----正在进行计算，此处只会执行一次------");
                    return i * 100;
                });
        System.out.println(lazy.isEvaluated());
        System.out.println(lazy.get()); //触发计算
        System.out.println(lazy.isEvaluated());
        System.out.println(lazy.get());//不会重新计算，返回上次结果

    }

    @Test
    public void testFuture() {
        System.out.println("当前线程名称：" + Thread.currentThread().getName());
        Integer result = Future.of(() -> {
            System.out.println("future线程名称：" + Thread.currentThread().getName());
            Thread.sleep(2000);
            return 100;
        })
                .map(i -> i * 10)
                .await(3000, TimeUnit.MILLISECONDS) //等待线程执行3000毫秒
                .onFailure(e -> e.printStackTrace())
                .getValue() //返回Optional<Try<Integer>>类型结果
                .getOrElse(Try.of(() -> 100)) //如果Option 为 empty时，则返回Try(100)
                .get();
        System.out.println(result); // 1000

    }

    @Test
    public void testMatch() {
        int i = 1;
        String s = Match(i).of(
                Case($(1), "one"), //等值匹配
                Case($(2), "two"),
                Case($(), "?")
        );
        String b = Match(i).of(
                Case($(is(1)), "one"), //谓词表达式匹配
                Case($(is(2)), "two"),
                Case($(), "?")
        );
        String arg = "-h";
        Match(arg).of(
                Case($(isIn("-h", "--help")), o -> run(this::displayHelp)), //支持在成功匹配后执行动作
                Case($(isIn("-v", "--version")), o -> run(this::displayVersion)),
                Case($(), o -> run(() -> {
                    throw new IllegalArgumentException(arg);
                }))
        );
        A obj = new A();
/*        Match(obj).of(
                Case($(instanceOf(Integer.class)), m -> new AtomicInteger(m)), //根据值类型进行匹配
                Case($(instanceOf(Double.class)), d -> new AtomicDouble(d)),
                Case($(), o -> {
//                    throw new NumberFormatException();
                    return  new AtomicInteger(1);
                })
        );*/

        Tuple2 tuple2 = Tuple("a", 2);
        Try<Tuple2<String, Integer>> _try = Try.success(tuple2);
        Match(_try).of(
                Case($Success($Tuple2($("a"), $())), tuple22 -> 234),
                Case($Failure($(instanceOf(Error.class))), error -> error.fillInStackTrace())
        );

        Option option = Option.some(1);
        Match(option).of(
                Case($Some($()), "defined"),
                Case($None(), "empty")
        );
    }

    public void displayHelp() {
        System.out.println("displayHelp");
    }

    public void displayVersion() {
        System.out.println("displayVersion");
    }

}

