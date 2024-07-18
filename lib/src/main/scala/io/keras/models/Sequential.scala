package io.keras.models

import io.keras.layers.Layer

import scala.collection.mutable
import scala.collection.mutable.Stack
/**
 * `Sequential` groups a linear stack of layers into a `Model`.
 *
 * Examples:
 *
 * {{{python
 *model = keras.Sequential()
 *model.add(keras.Input(shape=(16,)))
 *model.add(keras.layers.Dense(8))
 *
 *# Note that you can also omit the initial `Input`.
 *# In that case the model doesn't have any weights until the first call
 *# to a training/evaluation method (since it isn't yet built):
 *model = keras.Sequential()
 *model.add(keras.layers.Dense(8))
 *model.add(keras.layers.Dense(4))
 *# model.weights not created yet
 *
 *# Whereas if you specify an `Input`, the model gets built
 *# continuously as you are adding layers:
 *model = keras.Sequential()
 *model.add(keras.Input(shape=(16,)))
 *model.add(keras.layers.Dense(8))
 *len(model.weights)  # Returns "2"
 *
 *# When using the delayed-build pattern (no input shape specified), you can
 *# choose to manually build your model by calling
 *# `build(batch_input_shape)`:
 *model = keras.Sequential()
 *model.add(keras.layers.Dense(8))
 *model.add(keras.layers.Dense(4))
 *model.build((None, 16))
 *len(model.weights)  # Returns "4"
 *
 *# Note that when using the delayed-build pattern (no input shape specified),
 *# the model gets built the first time you call `fit`, `eval`, or `predict`,
 *# or the first time you call the model on some input data.
 *model = keras.Sequential()
 *model.add(keras.layers.Dense(8))
 *model.add(keras.layers.Dense(1))
 *model.compile(optimizer='sgd', loss='mse')
 *# This builds the model for the first time:
 *model.fit(x, y, batch_size=32, epochs=10)
 * }}}
 */
class Sequential(layers: mutable.Stack[Layer] = mutable.Stack(), trainable: Boolean=true, name: Option[String]=None) extends Model {


  override def call(): Unit = {

  }

  // override def layers() = layers;


  override def summary(
               line_length: Option[Int] = None,
               positions: Option[List[Double]] = None,
               print_fn: Option[Object] = None,
               expand_nested: Boolean = false,
               show_trainable: Boolean = false,
               layer_range: Option[List[String]] = None
             ): Unit = {

  }

  def add(layer: Layer, rebuild: Boolean=true): Unit = {

  }

  def pop() = {

  }

  private def maybeRebuild(): Unit = {

  }

  private def lockState(): Unit = {

  }

  def build(): Unit = {

  }



  def computeOutputSpec(): Unit = {

  }

  def inputShape(): Unit = {

  }

  def outputShape(): Unit = {

  }

  def inputs(): Unit = {

  }

  def outputs(): Unit = {

  }

  private def isLayerNameUnique() : Boolean = {
    true;
  }

  def getConfig(): Unit = {

  }

  def fromConfig(): Unit = {

  }


}

