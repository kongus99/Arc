package org.kon.game.phase

class Board1(value: Map[Int, Set[Int]], obstructions: List[Int]) {

  def noPathObstructions(path: List[Int]): Boolean = !containsObstructions(path)

  def pathIsConnected(start: Int, path: List[Int]): Boolean =
    path.foldLeft((start, true): Pair[Int, Boolean])((a: Pair[Int, Boolean], b: Int) => (b, contains(a._1, b) && a._2))._2

  def contains(pair: Pair[Int, Int]): Boolean =
    value.get(pair._1) match {
      case None => false
      case Some(f) => f.contains(pair._2)
    }

  private def containsObstructions(path: List[Int]): Boolean = path match {
    case Nil => false
    case h :: tail => obstructions.contains(h) || containsObstructions(tail)
  }

}

object BoardFactory {
  def create(mappings: List[Pair[Int, Int]], obstructions: List[Int]): Board1 = {
    val reversed = mappings.map(e => (e._2, e._1))
    new Board1((mappings ::: reversed).groupBy(e => e._1).mapValues(e => e.map(x => x._2).toSet), obstructions)
  }
}
