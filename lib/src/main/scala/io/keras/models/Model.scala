package io.keras.models

import io.keras.layers.Layer

import scala.collection.mutable

/** A model grouping layers into an object with training/inference features.
  *
  * There are three ways to instantiate a `Model`:
  *
  * ## With the "Functional API"
  *
  * You start from `Input`, you chain layer calls to specify the model's forward
  * pass, and finally, you create your model from inputs and outputs:
  *
  * {{{python
  * inputs = keras.Input(shape=(37,))
  * x = keras.layers.Dense(32, activation="relu")(inputs)
  * outputs = keras.layers.Dense(5, activation="softmax")(x)
  * model = keras.Model(inputs=inputs, outputs=outputs)
  * }}}
  *
  * Note: Only dicts, lists, and tuples of input tensors are supported. Nested
  * inputs are not supported (e.g. lists of list or dicts of dict).
  *
  * A new Functional API model can also be created by using the intermediate
  * tensors. This enables you to quickly extract sub-components of the model.
  *
  * Example:
  *
  * {{{python
  * inputs = keras.Input(shape=(None, None, 3))
  * processed = keras.layers.RandomCrop(width=128, height=128)(inputs)
  * conv = keras.layers.Conv2D(filters=32, kernel_size=3)(processed)
  * pooling = keras.layers.GlobalAveragePooling2D()(conv)
  * feature = keras.layers.Dense(10)(pooling)
  *
  * full_model = keras.Model(inputs, feature)
  * backbone = keras.Model(processed, conv)
  * activations = keras.Model(conv, feature)
  * }}}
  *
  * Note that the `backbone` and `activations` models are not created with
  * `keras.Input` objects, but with the tensors that originate from
  * `keras.Input` objects. Under the hood, the layers and weights will be shared
  * across these models, so that user can train the `full_model`, and use
  * `backbone` or `activations` to do feature extraction. The inputs and outputs
  * of the model can be nested structures of tensors as well, and the created
  * models are standard Functional API models that support all the existing
  * APIs.
  *
  * ## By subclassing the `Model` class
  *
  * In that case, you should define your layers in `__init__()` and you should
  * implement the model's forward pass in `call()`.
  *
  * {{{python
  * class MyModel(keras.Model):
  * def __init__(self):
  * super().__init__()
  * self.dense1 = keras.layers.Dense(32, activation="relu")
  * self.dense2 = keras.layers.Dense(5, activation="softmax")
  *
  * def call(self, inputs):
  * x = self.dense1(inputs)
  * return self.dense2(x)
  *
  * model = MyModel()
  * }}}
  *
  * If you subclass `Model`, you can optionally have a `training` argument
  * (boolean) in `call()`, which you can use to specify a different behavior in
  * training and inference:
  *
  * {{{python
  * class MyModel(keras.Model):
  * def __init__(self):
  * super().__init__()
  * self.dense1 = keras.layers.Dense(32, activation="relu")
  * self.dense2 = keras.layers.Dense(5, activation="softmax")
  * self.dropout = keras.layers.Dropout(0.5)
  *
  * def call(self, inputs, training=False):
  * x = self.dense1(inputs)
  * x = self.dropout(x, training=training)
  * return self.dense2(x)
  *
  * model = MyModel()
  * }}}
  *
  * Once the model is created, you can config the model with losses and metrics
  * with `model.compile()`, train the model with `model.fit()`, or use the model
  * to do prediction with `model.predict()`.
  *
  * ## With the `Sequential` class
  *
  * In addition, `keras.Sequential` is a special case of model where the model
  * is purely a stack of single-input, single-output layers.
  *
  * {{{python
  * model = keras.Sequential([
  * keras.Input(shape=(None, None, 3)),
  * keras.layers.Conv2D(filters=32, kernel_size=3),
  * ])
  * }}}
  */
trait Model {
  def call(): Unit;
  // def layers(): mutable.Stack[Layer]

  /** Prints a string summary of the network.
    *
    * @param line_length Total length of printed lines
    *                                        (e.g. set this to adapt the display to different
    *                                        terminal window sizes).
    * @param positions Relative or absolute positions of log elements
    *                                    in each line. If not provided, becomes
    *                                    `[0.3, 0.6, 0.70, 1.]`. Defaults to `None`.
    * @param print_fn Print function to use. By default, prints to `stdout`.
    *                                  If `stdout` doesn't work in your environment, change to `print`.
    *                                  It will be called on each line of the summary.
    *                                  You can set it to a custom function
    *                                  in order to capture the string summary.
    * @param expand_nested Whether to expand the nested models.
    *                                            Defaults to `False`.
    * @param show_trainable Whether to show if a layer is trainable.
    *                                              Defaults to `False`.
    * @param layer_range a list or tuple of 2 strings,
    *                                        which is the starting layer name and ending layer name
    *                                        (both inclusive) indicating the range of layers to be printed
    *                                        in summary. It also accepts regex patterns instead of exact
    *                                        name. In such case, start predicate will be the first element
    *                                        it matches to `layer_range[0]` and the end predicate will be
    *                                        the last element it matches to `layer_range[1]`.
    *                                        By default `None` which considers all layers of model.
    */
  def summary(
      line_length: Option[Int] = None,
      positions: Option[List[Double]] = None,
      print_fn: Option[Object] = None,
      expand_nested: Boolean = false,
      show_trainable: Boolean = false,
      layer_range: Option[List[String]] = None
  ): Unit;
}
