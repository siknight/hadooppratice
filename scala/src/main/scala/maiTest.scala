object maiTest {
  def main(args : Array[String]):Unit ={
    var sumVal = 21 ;
    val value: Any = if(sumVal > 20){
      "结果大于20"
    }
    println(value)
  }

  def self(){
    println("self my")
  }
}

class aa{
  def haha: Unit ={
    println("hhaha")
  }
}
