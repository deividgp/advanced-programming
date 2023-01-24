package part2

import part1.DataFrameDirectory

import dataframe.*

class CounterVisitor extends Visitor {
  var files = 0
  var dirs = 0

  override def visit(d: DataFrame): Unit = d match {
    case _: DataFrameFile => files += 1
    case _: DataFrameDirectory => dirs += 1
  }
}
