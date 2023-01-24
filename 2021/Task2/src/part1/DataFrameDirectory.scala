package part1

import scala.collection.mutable.ListBuffer
import dataframe.DataFrame
import scala.jdk.CollectionConverters._
import part2.Visitor

class DataFrameDirectory(val name: String) extends DataFrame{
  private var dataFrameList: ListBuffer[DataFrame] = new ListBuffer[DataFrame]()

  def addChild(child: DataFrame): Unit = {
    dataFrameList += child
  }

  def removeChild(child: DataFrame): Unit = {
    dataFrameList -= child
  }

  def getChildren: List[DataFrame] = dataFrameList.toList

  def whichClassDirectory(): Unit ={
    println("I am DataFrameDirectory")
  }

  override def at(row: Int, column: String): java.util.List[Object] = {
    dataFrameList.flatMap(_.at(row, column).asScala.toList).asJava
  }

  override def columns: java.util.List[Integer] = {
    dataFrameList.flatMap(_.columns.asScala.toList).asJava
  }

  override def size: java.util.List[Integer] = {
    dataFrameList.flatMap(_.size.asScala.toList).asJava
  }

  override def accept(v: Visitor): Unit = {
    v.visit(this)
    dataFrameList.foreach(_.accept(v))
  }
}