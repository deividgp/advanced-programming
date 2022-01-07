import dataframe.{CSVDataFrameFactory, DataFrameAbstract}
import part1.DataFrameDirectory
import part2.Visitor

import scala.annotation.tailrec
import scala.language.postfixOps
import scala.jdk.CollectionConverters.*

object Main extends App {
  val CSVFactory = new CSVDataFrameFactory()
  val dataframe = CSVFactory.createDataFrame("cities.csv", "")
  val listFloat = dataframe.getColumnList("LatD").asScala.toList
  val listString = dataframe.getColumnList("City").asScala.toList

  val hola = listFilterMapTail[String, String](a => a.contains("Winnipeg"), a => a.replace("Winnipeg", "hola"), listString.asInstanceOf[List[String]])
  val hola2 = listFilterMapTail[Float, Int](a => a > 100, a => a.round, listFloat.asInstanceOf[List[Float]])
  val hola3 = listFilterMapStack[String, String](a => a.contains("Winnipeg"), a => a.replace("Winnipeg", "hola"), listString.asInstanceOf[List[String]])
  val hola4 = listFilterMapStack[Float, Int](a => a > 100, a => a.round, listFloat.asInstanceOf[List[Float]])

  def listFilterMapStack[A,B](condition: A => Boolean, operation: A => B, listA: List[A]) : List[B] = listA match {
    case Nil => Nil
    case x :: xs =>
      if(condition(x)){
        listFilterMapStack[A,B](condition, operation, xs) :+ operation(x)
      }else{
        listFilterMapStack[A,B](condition, operation, xs)
      }
  }

  def listFilterMapTail[A,B](condition: A => Boolean, operation: A => B, listA: List[A]) : List[B] = {
    @tailrec
    def listFilterMapTailAux(listA: List[A], result: List[B]) : List[B] = listA match {
      case Nil => result
      case x :: xs =>
        if(condition(x)){
          listFilterMapTailAux(xs, result :+ operation(x))
        }else{
          listFilterMapTailAux(xs, result)
        }
    }
    listFilterMapTailAux(listA, Nil)
  }

  // MultipleInheritance
  class Combination extends DataFrameDirectory with Visitor
  val c = new Combination()
  c.whichClassDirectory()
  c.whichClassVisitor()

  // Curry implementation
  def listFilterMapStackCurry[A,B](condition: A => Boolean, listA: List[A]) (operation:(A => B)) : List[B] = listA match {
    case Nil => Nil
    case x :: xs =>
      if(condition(x)){
        listFilterMapStack[A,B](condition, operation, xs) :+ operation(x)
      }else{
        listFilterMapStack[A,B](condition, operation, xs)
      }
  }

  def replace = (a:String) => a.replace("Winnipeg", "hola")
  var hola5 = listFilterMapStackCurry[String, String](a => a.contains("Winnipeg"), listString.asInstanceOf[List[String]])(replace)
  print(hola5)
}
