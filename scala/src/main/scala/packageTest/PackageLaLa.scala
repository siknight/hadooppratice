package packageTest

package PackageLaLa {
  import two.three.tiger
  package two{
    class cat{

    }
    package three{
       class tiger{

       }
    }
    object mainT{
      def main(args: Array[String]): Unit = {
       val tiger = new tiger
      }
    }
  }
}
