package io.keras.layers.rnn

import io.keras.layers.Layer
import org.tensorflow.Tensor

class RNN extends Layer{

  override def build(inputShape: Array[Int]): Unit = ???

  override def call(inputs: Tensor): Tensor = ???


}
