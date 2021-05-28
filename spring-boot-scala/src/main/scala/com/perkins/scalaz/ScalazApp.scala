package com.perkins.scalaz

import scalaz._
import std.option._, std.list._ // functions and type class instances for Option and List

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


}
