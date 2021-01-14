import java.util

import objecttest.Dog

import scala.math._
import scala.util.control.Breaks
object Bb {
  def a(): Unit ={
    val javaUtil = new util.ArrayList[String]()
    javaUtil.add("aa")
    println(javaUtil)
  }
}

object Aa{
  def main(args: Array[String]): Unit = {
    val dog = new two.Dog(2)
    println(dog)
    val dog2 =new Dog(3)
    println(dog2)
//    new SayHi().hi()
//    Bb.a()
//    val bb: Bb.type = Bb
//    bb.a()
//  @throws(classOf[NumberFormatException])
//  def f11={
//    "abc".toInt;
//  }
//  def main(args: Array[String]): Unit = {
//    f11
//  }
//    try{
//      10/0
//    }catch {
//      case ex:ArithmeticException =>println("算术异常")
//      case ex:Exception => println("异常")
//    }
  }
}