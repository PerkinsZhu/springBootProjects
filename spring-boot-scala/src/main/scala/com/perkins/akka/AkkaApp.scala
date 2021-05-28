package com.perkins.akka

import akka.stream._
import akka.stream.scaladsl._
import akka.{Done, NotUsed}
import akka.actor.ActorSystem
import akka.util.ByteString
import org.junit.Test

import scala.concurrent._
import scala.concurrent.duration._
import java.nio.file.Paths

class AkkaApp {
  implicit val system: ActorSystem = ActorSystem("QuickStart")

  @Test
  def testStream(): Unit = {
    val source: Source[Int, NotUsed] = Source(1 to 100)
    val done: Future[Done] = source.runForeach(i => println(i))

    implicit val ec = system.dispatcher
    done.onComplete(_ => system.terminate())

    val factorials = source.scan(BigInt(1))((acc, next) => acc * next)

    val result: Future[IOResult] =
      factorials.map(num => ByteString(s"$num\n")).runWith(FileIO.toPath(Paths.get("factorials.txt")))

    result.value match {
      case _ =>
    }


    Await.ready(done, Duration.Inf)
  }

}
