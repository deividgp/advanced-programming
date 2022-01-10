package part2

import part1.DataFrameDirectory
import dataframe.*
import java.*
import scala.jdk.CollectionConverters.*

class FilterVisitor(test: java.util.HashMap[String, AnyRef] => Boolean) extends Visitor {
  var elements: List[Object] = Nil

  override def visit(d: DataFrame): Unit = d match {
    case dataframe: DataFrameFile => dataframe.getDataFrame.asScala.toList.foreach(m => if (test(m)) elements = m :: elements)
    case _: DataFrameDirectory =>
  }
}
