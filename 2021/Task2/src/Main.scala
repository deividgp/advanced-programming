import dataframe.{CSVDataFrameFactory, DataFrameFile}
import part1.DataFrameDirectory
import part2.{CounterVisitor, FilterVisitor, Visitor}

import java.util
import java.util.HashMap
import scala.annotation.tailrec
import scala.language.postfixOps
import scala.jdk.CollectionConverters.*

object Main extends App {
  val CSVFactory = new CSVDataFrameFactory()
  val dataframe = CSVFactory.createDataFrame("cities.csv", "")
  val dataframe2 = CSVFactory.createDataFrame("cities.csv", "")
  val listDouble = dataframe.getColumnValues("LatD").asScala.toList
  val listString = dataframe.getColumnValues("City").asScala.toList
  val directory = new DataFrameDirectory("directori")
  directory.addChild(dataframe)
  directory.addChild(dataframe2)

  val v = new FilterVisitor((e: util.HashMap[String, AnyRef]) => e.get("LatD").asInstanceOf[Double] >= 39)
  dataframe.accept(v)
  println("Filtered: " + v.elements)

  val c = new CounterVisitor()
  dataframe.accept(c)
  println("DataFrame files: " + c.files + " DataFrame dirs: " + c.dirs)

  val stringMapTail = listFilterMapTail[String, String](a => a.contains("Winnipeg"), a => a.replace("Winnipeg", "hola"), listString.asInstanceOf[List[String]])
  val numberMapTail = listFilterMapTail[Double, Int](a => a > 100, a => a.round.toInt, listDouble.asInstanceOf[List[Double]])
  val stringMapStack = listFilterMapStack[String, String](a => a.contains("Winnipeg"), a => a.replace("Winnipeg", "hola"), listString.asInstanceOf[List[String]])
  val numberMapStack = listFilterMapStack[Double, Int](a => a > 100, a => a.round.toInt, listDouble.asInstanceOf[List[Double]])

  println(stringMapTail)
  println(numberMapTail)
  println(stringMapStack)
  println(numberMapStack)

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

  // Fold

  // For loops
  val list = directory.getChildren
  val result = for {
    d <- list
    n = d.at(0, "LatD")
  } yield n
  println(result)

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
  var mapStackCurry = listFilterMapStackCurry[String, String](a => a.contains("Winnipeg"), listString.asInstanceOf[List[String]])(replace)
  print(mapStackCurry)
}
