package com.spark.demo

/**
 * Scala语法学习：
 * http://alanwu.iteye.com/blog/478953
 * http://www.cnblogs.com/wei-li/p/ScalaFirstSight.html
 * 这个demo中包含了：
 * 1.   函数定义
 * 2.   变量定义
 * 3.   类型定义
 * 4.   函数当成变量传递
 * 5.   循环定义：（while、for、foreach）
 * 6.   匿名函数
 * 7.
 * 8.
 * 9.
 * 10.
 *
 * Created by phwang on 2017/2/10.
 */
object ScalaDemo {

  def main(args: Array[String]) {

    // 定义变量用val和var，val是不变的变量，var是可变的变量
    val maxValue = max(20, 189)

    println(maxValue)

    // WARN : 方法也是可以当成参数来传递的， 在方法的后面加个下划线，就可以了。
    val m = max _
    println("max value:%d".format(m(32, 190)))

    showLoop()
  }


  /**
  =============
  =   数组：   =
  =============
  */
  def array(): Unit = {

  }


  /**
  =============
  =   循环：   =
  =============
    */

  def showLoop(): Unit = {
    // 定义一个数组
    val ar = new Array[String](3)
    ar(0) = "a"
    ar(1) = "b"
    ar(2) = "c"


    // while 循环
    println("============================")
    println("while loop:")
    var i = 0
    while (i < ar.length) {
      // XXX scala里面不能用 i++ 和 ++i
      println(ar(i))
      i += 1
    }

    // for 循环--------------------------
    println("============================")
    println("for loop :for(arg <- args) println(arg):")
    for (a <- ar) {
      println(a)
    }

    // foreach 循环   => 是个方法体
    println("============================")
    println("foreach loop:")
    ar.foreach(a => println(a))

    // 这里是把形参的类型也加上了， (a: String) => println(a)
    // (a: String) => println(a) 这个整体，是个匿名方法：方法参数 => 方法体
    ar.foreach((a: String) => println(a))

    // 匿名方法中，唯一的参数，传给唯一的方法，还可以更牛逼：
    // ar.foreach(println)
    ar.foreach(println)
  }


  /**
  =============
    =   定义方法：=
    =============
    方法签名描述：def开头 max是方法名(a是变量名: Int是变量的类型):Int是返回类型，如果Unit是java中的void = {
        XXX
        XXX
        方法体
     }
    */
  def max(a: Int, b: Int): Int = {
    if (a > b)
      a
    else
      b
  }
}
