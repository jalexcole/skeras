package io.keras.layers.rnn

import io.keras.layers.Layer
import org.tensorflow.Tensor

class Bidirectional extends Layer{
override def build(inputShape: Array[Int]): Unit = ???

  override def call(inputs: Tensor): Tensor = ???
}
