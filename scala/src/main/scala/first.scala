object Hello {
  def main(args: Array[String]): Unit =  {
//    println("Hello World")
    val name = "ApacheCN"
    val age  = 1
    val url  = "www.atguigu.com"
    //1.几种打印的区别
    printf("name=%s,age=%d,url=%s\n",name,age,url)
    println(s"name=$name,age=${age},url=${url}")
    //2.变量
    var a = 222;
    var b:Double = 2;
    println("a="+a)
    println(b)
    var char01 : Char = 'b';
    println(char01.toInt)
    var d = 12l
    println("d=" + d)
//    var f1 : Float = 1.1    
    var f2 = 1.2
    println("f2="+f2)
    var f3 : Double = 1.3
    var f4 : Float = 1.4f
    var f5 : Double = 1.5f
    var u =()
    println(s"u=$u")
    //------------
    def f1():Nothing = {
      throw new Exception()
    }

    var num : Int = 2.7.toInt
    println("num="+num)
  }
}
