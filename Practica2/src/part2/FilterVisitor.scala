//package part2
//
//import part1.DataFrameDirectory
//
//import java.*
//
//class FilterVisitor(test: Object => Boolean) extends Visitor {
//  var dataframes: List[Object] = Nil
//
//  override def visit(d: DataFrame): Unit = d match {
//    case dataframe: DataFrameAbstract => dataframe.getDataFrame.foreach(m => if (test(m)) messages = m :: messages) // messages:::List(m)
//    case _: DataFrameDirectory =>
//  }
//}
