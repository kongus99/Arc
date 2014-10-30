package org.kon.game.phase

class Board1[T](mappings: List[Pair[T, T]], obstructions: List[T]) {

  private val value: Map[T, Set[T]] = {
    val reversed = mappings.map(e => (e._2, e._1))
    (mappings ::: reversed).groupBy(e => e._1).mapValues(e => e.map(x => x._2).toSet)
  }

  def noPathObstructions(path: List[T]): Boolean = !containsObstructions(path)

  def pathIsConnected(start: T, path: List[T]): Boolean =
    path.foldLeft((start, true): Pair[T, Boolean])((a: Pair[T, Boolean], b: T) => (b, contains(a._1, b) && a._2))._2

  def contains(pair: Pair[T, T]): Boolean =
    value.get(pair._1) match {
      case None => false
      case Some(f) => f.contains(pair._2)
    }

  private def containsObstructions(path: List[T]): Boolean = path match {
    case Nil => false
    case h :: tail => obstructions.contains(h) || containsObstructions(tail)
  }

}
