package com.perkins

import com.perkins.NonZero.create
import com.perkins.NoneZeroOps.toNoneZeroOps
import org.junit.Test

/**
 *
 * @author: perkins Zhu
 * @date: 2021/6/8 15:25
 * @description:
 * */
trait NonZero[T] {
  def nonZero(t: T): Boolean
}

/*object NonZero {
  def create[T](t: T)(f: T => Boolean) = {
    f(t)
  }
}*/
object NonZero {
  def create[A](f: A => Boolean): NonZero[A] = new NonZero[A] {
    def nonZero(a: A): Boolean = f(a)
  }

  implicit val intNZInstance: NonZero[Int] = create {
    case 0 => false
    case _ => true
  }
}

class NoneZeroOps[T](t: T)(implicit nonZero: NonZero[T]) {
  def isNoneZero: Boolean = nonZero.nonZero(t)
}

object NoneZeroOps {
  implicit def toNoneZeroOps[T](t: T)(implicit nonZero: NonZero[T]): NoneZeroOps[T] = new NoneZeroOps[T](t)
}

class test {

  @Test
  def testOps(): Unit = {
    //使用的自定义判断逻辑
    val b = NonZero.create((i: Int) => i == 10);
    println(b.nonZero(23))

    //使用的默认判断逻辑 com.perkins.NonZero.intNZInstance
    a(23)
    println(23.isNoneZero)

  }

  def a(nonZero: NoneZeroOps[Int]): Unit = {
    println("====" + nonZero.isNoneZero)
  }

}