package org.kon.game.phase

import org.kon.game.board.{BoardField, StableBoardField, UnstableBoardField}

class Board1(value: Map[Int, BoardField], obstructions: List[Int]) {

  def noPathObstructions(path: List[Int]): Boolean = !containsObstructions(path)

  def pathIsConnected(start: Int, path: List[Int]): Boolean =
    path.foldLeft((start, true): Pair[Int, Boolean])((a: Pair[Int, Boolean], b: Int) => (b, contains(a._1, b) && a._2))._2

  private def contains(pair: Pair[Int, Int]): Boolean =
    value.get(pair._1) match {
      case None => false
      case Some(f) => f.hasNeighbor(pair._2)
    }

  private def containsObstructions(path: List[Int]): Boolean = path match {
    case Nil => false
    case h :: tail => obstructions.contains(h) || containsObstructions(tail)
  }

}

object BoardFactory {
  def create(mappings: List[Pair[Int, Int]], obstructions: List[Int], stableFields: List[Int]): Board1 = {
    val allMappings = mappings ::: mappings.map(e => (e._2, e._1))
    val groupedByFirst: Map[Int, List[(Int, Int)]] = allMappings.groupBy(e => e._1)
    val fieldFactory: (Int, Set[Int]) => BoardField = (x, y) => if (stableFields.contains(x)) new StableBoardField(y, 0) else new UnstableBoardField(y, 0)
    val mappedConnections: Map[Int, Set[Int]] = groupedByFirst.mapValues(e => e.map(x => x._2).toSet)
    new Board1(mappedConnections.toList.map(e => (e._1, fieldFactory(e._1, e._2))).toMap, obstructions)
  }
}
