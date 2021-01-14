object FuncTest {
  //函数式编程30页ppt
  def main(args: Array[String]): Unit = {
    @throws(classOf[NumberFormatException])
    def f11()  = {
      "abc".toInt
    }

    println(f11())
    //    try {
//      val r = 10 / 0
//    } catch {
//        case ex2: ArithmeticException=> println("捕获了除数为零的算术异常="+ex2)
//        case ex: Exception => println("捕获了异常")
//    } finally {
//        // 最终要执行的代码
//        println("scala finally...")
//    }


        //    def sum(n1 : Int, n2 : Int): Int = {
//      println("sum() 执行了..")
//      return  n1 + n2
//    }
//
//      lazy val res = sum(10, 20)
////      val res = sum(10, 20)
//      println("-----------------")
//      println("res=" + res)
    //    val list = List(1, 2, 3, 4)
//    val strings: List[String] = list.map((x: Int) => {
//      x + "q"
//    })
//    println(list.map(_+1));


    //
//    def aa(f:Int=>Int,b:Int) = f(b)
//
//    def f=(a:Int)=>a+2;
//
//    println(f(2))
//    println(aa(f,3))
//    var money = 100
//    def buy(): Int = {
//      money  -= 10
//      money
//    }
//    def test1(a: Int) = {   //传值调用
//      println(a)
//      println(a)
//    }
//    def test2(a: => Int) = {  //传名调用
//      println(a)
//      println(a)
//    }

//      test1(buy)
//      test2(buy)



//   val f2:(Int,Double)=>Double = (a,b)=>a.toInt + b.toDouble;
//
//    println(f2(2,4.2))
//
//    val f3= (a:Int,b:Double)=>a.toInt + b.toDouble;
//    println(f3(2,4.2))
    //    val list =List(1,2,3,4);
//    val  map = list.map(_+1);
//    println(map)
//    val ints: List[Int] = list.map(x => x + 1)
//    println("ints="+ints)
//    println(list)
//    def addMulti(a: Int) = {
//      (b: Int) => (c: Int) => (a + b) * c
//    }

//    println(addMulti(1)(2)(3))
//    def addBy(n:Int) ={
//      (d : Double) => d+n
//    }
//
//    println(addBy(2)(3))
//    def apply01(f:Int=>String,v:Int)= f(v)
//    def haha(a:Int)={
//      a+",,,,"
//    }
//    println(apply01(haha,3))
//    def f1 = "venassa"
//    println(f1) //

    //    var money = 100
//    def buy(): Int = {
//      money  -= 10
//      money
//    }
//    def test1(a: Int) = {
//      println(a)
//      println(a)
//    }
//
//    def test2(a: => Int) = {
//      println(a)
//      println(a)
//    }



//
//    test1(buy)
//    test2(buy)



    //    val f1 = () =>"abc"
//
//    //(Int,Int) => Int是一个函数类型
//    val f2:(Int,Int) => Int =(a,b)=>a + b;
//
//    val f3 = (a:Int,b:Int)=>a+b;
//    println(f2(1,2))
//
//
//    println(f3(2,3))


//    println(f1())

//    def f1(): Int = {100}
//    println(f1)
//    var f2 = f1
//    var f3 = f1()
//    var f4 = f1 _
//
//    println(f2)
//    println(f3)
//    println(f4)
//    println(f4())

  }
}
