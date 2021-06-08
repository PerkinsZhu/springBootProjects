package com.perkins.scalaztest

import org.junit.Test
import scalaz._
import std.option._
import std.list._ // functions and type class instances for Option and List

/**
 *
 * @author: perkins Zhu
 * @date: 2021/5/28 13:04
 * @description
 * */
object ScalazApp {

  def main(args: Array[String]): Unit = {
    simpleTest()
  }

  def simpleTest(): Unit = {
    val a = Apply[Option].apply2(some(1), some(2))((a, b) => a + b)
    //  .getOrElse(22)
    //    val a = Some(2)
    println(a)
    Traverse[List].traverse(List(1, 2, 3))(i => some(i)).foreach(list => println(list))
  }

  def tell[T](t: T)(M: Tellable[T]) = {
    M.tell(t)
  } //> tell: [T](t: T)(M: scalaz.learn.demo.Tellable[T])String

  println(tell(Color("RED"))(colorTeller))




}

trait Tellable[T] {
  def tell(t: T): String
}

case class Color(descript: String)

case class Person(name: String)

object colorTeller extends Tellable[Color] {
  override def tell(t: Color): String = "I am color " + t.descript
}

