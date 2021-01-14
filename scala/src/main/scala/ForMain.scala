import  util.control.Breaks._
//class ForMain{
//
//}
object ForMain {
  def main(args: Array[String]): Unit = {
    var n= 10;
    breakable{
      while(n<20){
          n=n+1;
          if(n==18){
            break()
          }
      }
    }
   println(n)

  }
  def gou(): Unit ={
    println("dog......")
  }
}
