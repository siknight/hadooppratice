package objecttest

import scala.beans.BeanProperty

class Dog(var age:Int) {

  def this(food:String){
   this(22);
   this.food =food;
  }
   private var sal:Double = _
   var food:String = _
  @BeanProperty
   var color:String = _
   def cal(n1:Int,n2:Int)={
      n1+n2
   }
}

object MainTest{
  def main(args: Array[String]): Unit = {
    val dog = new Dog(22)
    dog.setColor("red")
    println(dog.getColor)
    dog.color = "blue"
    print(dog.color)
//    dog.food="苹果"
//    println(dog.food)
//    dog.age=88
//    val dog2=dog;
//    print("dog="+dog.age+",dog2="+dog2.age)
//    println(dog.age)
//    println(dog.cal(1, 2))
//    val dog2 = new Dog(22,"haha")
//    dog2.food="橘子"
//    println(s"dog1=${dog.food},dog2=${dog2.food}")
  }
}
