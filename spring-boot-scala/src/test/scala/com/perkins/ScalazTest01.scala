package com.perkins

import org.junit.Test
import scalaz.{Equal, Order}
import scalaz._
import scalaz.Scalaz._

/**
 *
 * @author: perkins Zhu
 * @date: 2021/6/8 13:35
 * @description:
 * */
class ScalazTest {

  @Test
  def testImplicit(): Unit = {

    implicit object listTeller extends Tellable[List[Color]] {
      def tell(t: List[Color]): String = {
        (t.map(c => c.descript)).mkString("I am list of color [", ",", "]")
      }
    }

    type AAA = List[Color]
    //    tell[List[Color]](List(Color("RED"), Color("BLACK"), Color("YELLOW"), Color("BLUE")))
    val res = tell[AAA](List(Color("RED"), Color("BLACK"), Color("YELLOW"), Color("BLUE")))
    println(res)
    println(123)
  }

  /*  def tell[T](t: T)(implicit M: Tellable[T]) = {
      M.tell(t)
    }*/

  def tell[T: Tellable](t: T) = {
    implicitly[Tellable[T]].tell(t)
  }


  //  implicit val personEqual: Equal[Person] = Equal.equal { (a, b) => a.name == b.name && a.age == b.age }

  implicit val personEqual2 = new Equal[Person] {
    override def equal(a1: Person, a2: Person): Boolean = a1.name == a2.name && a1.age == a2.age
  }
  implicit val personEqual: Equal[Int] = Equal.equal { (a, b) => a == b }

  def moneyToInt(m: MoneyCents): Int = m.cents * 100

  //把 int比较 转化为 MoneyCents的比较
  implicit val moneyEqual: Equal[MoneyCents] = Equal.equalBy(moneyToInt)

  import scalaz.Ordering;

  implicit val personAgeOrder = new Order[Person] {
    def order(a1: Person, a2: Person): Ordering =
      if (a1.age < a2.age) Ordering.LT else if (a1.age > a2.age) Ordering.GT else Ordering.EQ
  }

  implicit val personShow: Show[Person] = Show.shows { p => p.name + "," + p.age + " years old" }
  val p3 = Person("Jone", 23)


  @Test
  def testEqual(): Unit = {
    val p1 = Person("Jone", 23)
    val p2 = Person("Jone", 23)
    println(p1 === p2)
    println(Person("Jone", 23) === Person("John", 23))
    println(MoneyCents(120) === MoneyCents(120))
    println(personShow.shows(p3))
    println(Person("Harry", 24).shows)
    println(('a' to 'e'))
    println('a' |-> 'e')

  }

}

case class MoneyCents(cents: Int)

case class Person(name: String, age: Int)

trait Tellable[T] {
  def tell(t: T): String
}

case class Color(descript: String)
