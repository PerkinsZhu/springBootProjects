package com.perkins

import com.perkins.NonZero.create
import com.perkins.NoneZeroOps.toNoneZeroOps
import org.junit.Test
import org.scalatest.Matchers.===
import scalaz.Functor
import scalaz.Scalaz.{ToEqualOps, listInstance}

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
  implicit val stringNZInstance: NonZero[String] = NonZero.create {
    case "" => false
    case _ => true
  } //> stringNZInstance  : scalaz.ex5.NonZero[String] = scalaz.ex5$NonZero$$anon$1@
  //| 1c655221
  implicit val booleanNZInstance: NonZero[Boolean] = NonZero.create { b => b }
  //> booleanNZInstance  : scalaz.ex5.NonZero[Boolean] = scalaz.ex5$NonZero$$anon$
  //| 1@6aaa5eb0

  implicit def listNZInstance[A]: NonZero[List[A]] = NonZero.create {
    case Nil => false
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
    //SD
    //使用的默认判断逻辑 com.perkins.NonZero.intNZInstance
    a(23)
    println(23.isNoneZero)
    println("23".isNoneZero)
    println(true.isNoneZero)
    println(List.empty[Int].isNoneZero)

  }

  def a(nonZero: NoneZeroOps[Int]): Unit = {
    println("====" + nonZero.isNoneZero)
  }

  @Test
  def testMap(): Unit = {
    Functor[List].map(List(1, 2, 3).map(i => i + 1))(i2 => i2 * 3)
    List(1, 2, 3).map(((i2: Int) => i2 * 3) compose ((i: Int) => i + 1))
    List(1, 2, 3).foreach(i => println(i))
  }
}