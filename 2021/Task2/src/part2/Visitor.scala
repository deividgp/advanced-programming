package part2

import dataframe.DataFrame

trait Visitor {
  def visit(d: DataFrame): Unit

  def whichClassVisitor(): Unit ={
    println("I am Visitor")
  }
}
