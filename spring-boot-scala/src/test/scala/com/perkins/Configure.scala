package com.perkins

import org.junit.Test
import org.scalactic.algebra.Functor.adapters
import org.scalactic.algebra.{Applicative, Functor}
/**
 *
 * @author: perkins Zhu
 * @date: 2021/6/21 18:59
 * @description:
 * */
trait Configure[+A] {
  def get: A
}

object Configure {
  def apply[A](data: => A) = new Configure[A] {
    override def get: A = data
  }

  implicit val configureFunctor = new Functor[Configure] {
    override def map[A, B](ca: Configure[A])(f: A => B): Configure[B] = Configure(f(ca.get))
  }
  implicit val configureApplicative = new Applicative[Configure] {
    override def insert[A](a: A): Configure[A] = Configure(a)

    override def applying[A, B](ca: Configure[A])(cab: Configure[A => B]): Configure[B] = cab map { fab => fab(ca.get) }
  }
}


class TestApp {
  @Test
  def test01(): Unit = {
    val a = Configure("jjjjjjjj")
    println(a.get)

  }

  //TEST01
}

