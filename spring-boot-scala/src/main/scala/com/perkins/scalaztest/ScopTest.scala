package com.perkins.scalaztest

import org.junit.Test

/**
 * 测试作用域屏蔽
 *
 * @author: perkins Zhu
 * @date: 2021/5/28 14:38
 * @description:
 * */
class ScopTest {
  /**
   * 隐式转换时判断用哪个scop中的转换器
   * 绑定屏蔽是分优先次序如下：
   * 1、本地声明、定义或者透过继承又或者在同一源代码文件内的package定义的绑定最优先
   * 2、明式申明的import如：import obj.Foo 所定义的绑定次优先
   * 3、通配符式的import如：import obj._ 所定义的绑定再次之
   * 4、同一package但处于不同源代码文件内的绑定最次优先
   */

  implicit val AtoB: A => B = a => new B(a.a)

  @Test
  def testClassScop(): Unit = {
    new A(2).fa()
  }

  @Test
  def testImplicit(): Unit = {
    val printB: B => Unit = println(_)
    printB(new A(23))
  }


}

class B(b: Int)

class A(val a: Int) {
  def fa(): Unit = {
    val a = 100
    println(a)
  }
}

