package io.keras.layers.core

import io.keras.layers.Layer
import org.tensorflow.Tensor

class InputLayer extends Layer{
    override def build(inputShape: Array[Int]): Unit = ???

  override def call(inputs: Tensor): Tensor = ???
}
