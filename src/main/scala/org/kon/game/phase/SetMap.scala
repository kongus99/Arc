package org.kon.game.phase

class SetMap[T](mappings: List[Pair[T, T]]) {
  private val value = {
    val reversed = mappings.map(e => (e._2, e._1))
    (mappings ::: reversed).groupBy(e => e._1).mapValues(e => e.map(x => x._2).toSet)
  }

  def contains(pair: Pair[T, T]): Boolean =
    value.get(pair._1) match {
      case None => false
      case Some(f) => f.contains(pair._2)
    }
}
