package io.keras.metrics

trait Metric {
  def auto_name(className: String): String = s"${className}_auto"

  def updateState(): Unit;
  def result(): Unit;
}

object ops {
  def cast(value: Any, dtype: String): Any = ???
  def logical_and(a: Any, b: Any): Any = ???
  def equal(a: Any, b: Any): Any = ???
  def multiply(a: Any, b: Any): Any = ???
  def sum(value: Any): Any = ???
  def broadcast_to(value: Any, shape: Seq[Int]): Any = ???
}

