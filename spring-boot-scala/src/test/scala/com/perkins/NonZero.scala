package com.perkins

import com.perkins.NonZero.{create, listNZInstance}
import com.perkins.NoneZeroOps.toNoneZeroOps
import org.junit.Test
import org.scalatest.Matchers.===
import scalaz.Functor
import scalaz.Scalaz.{ToEqualOps, function1Comonad, function1Covariant, function2Instance, function3Instance, listInstance}

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

  @Test
  def factorTest(): Unit = {
    implicit val item3Functor = new Functor[Item3] {
      def map[A, B](ia: Item3[A])(f: A => B): Item3[B] = Item3(f(ia.i1), f(ia.i2), f(ia.i3))
    }

    //| un$main$1$$anon$1@5e265ba4
    val F = Functor[Item3] //> F  : scalaz.Functor[scalaz.functor.Item3] = scalaz.functor$$anonfun$main$1$$
    //| anon$1@5e265ba4
    val a = F.map(Item3("Morning", "Noon", "Night"))(_.length) //> res0: scalaz.functor.Item3[Int] = Item3(7,4,5)
    val b = F.apply(Item3("Morning", "Noon", "Night"))(_.length) //> res1: scalaz.functor.Item3[Int] = Item3(7,4,5)
    val c = F(Item3("Morning", "Noon", "Night"))(_.length) //> res2: scalaz.functor.Item3[Int] = Item3(7,4,5)
    val d = F.lift((s: String) => s.length)(Item3("Morning", "Noon", "Night")) //> res3: scalaz.functor.Item3[Int] = Item3(7,4,5)
    println(a)
    println(b)
    println(c)
    println(d)

    val f = Functor[List] compose Functor[Item3]
    val aa = f.map(List.apply(Item3(1, 2, 3)))(i => i.toString)
    println(aa)

    val item3 = Item3("Morning", "Noon", "Night")
    val bb = f.map(List(item3, item3))(_.length)
    println(bb)

    //    https://www.cnblogs.com/tiger-xc/p/4840597.html
    val cc = Functor[({type l[x] = String => x})#l].map((s: String) => s + "!")(_.length)("Hello")
    println(cc)

    type a = {def close(): Unit}
    type l[x] = String => x
    val dd = Functor[({type l[x] = (String, Int) => x})#l]
      .map((s: String, i: Int) => s.length + i)(_ * 10)("Hello", 5)
    val ee = Functor[({type l[x] = (String, Int, Boolean) => x})#l]
      .map((s: String, i: Int, b: Boolean) => s + i.toString + b.toString)(_.toUpperCase)("Hello", 3, true)

    val list = scala.collection.mutable.ListBuffer[{type A= Int =>String}]()
    list.append((A:Int)=> "asdfasd")

    (1 to 10).toList.map(i => i.toString)

  }

}


case class Item3[A](i1: A, i2: A, i3: A)
