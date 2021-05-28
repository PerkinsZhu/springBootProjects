package com.perkins.thread

import org.junit.Test

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}

class FutureTest {

  @Test
  def testFuture(): Unit = {
    val future = Future.successful(100)
    future.map(_ * 10)
      .andThen {
        case _ => 234
      }
      .map(_ - 10)
      .onComplete {
        case Success(value) => println(value)
        case Failure(exception) => exception.printStackTrace()
      }

    Await.ready(future, Duration.Inf)
  }

  @Test
  def testAkkaStream(): Unit ={

  }
}
