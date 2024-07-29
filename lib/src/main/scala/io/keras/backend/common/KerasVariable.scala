package io.keras.backend.common

/** Represents a backend-agnostic variable in Keras.
  *
  * A `Variable` acts as a container for state. It holds a tensor value and can
  * be updated. With the JAX backend, variables are used to implement
  * "functionalization", the pattern of lifting stateful operations out of a
  * piece of computation to turn it into a stateless function.
  *
  * Args: initializer: Initial value or callable for initialization. If a
  * callable is used, it should take the arguments `shape` and `dtype`. shape:
  * Optional. Tuple for the variable's shape. Required if `initializer` is a
  * callable. dtype: Optional. Data type of the variable. Defaults to the global
  * float dtype type (`"float32"` if never configured). trainable: Optional.
  * Boolean indicating if variable is trainable. Defaults to `True`. name:
  * Optional. A unique name for the variable. Automatically generated if not
  * set.
  *
  * Attributes: name: The name of the variable (string). path: The path of the
  * variable within the Keras model or layer (string). dtype: The data type of
  * the variable (string). shape: The shape of the variable (tuple of integers).
  * ndim: The number of dimensions of the variable (integer). trainable: Whether
  * the variable is trainable (boolean). value: The current value of the
  * variable (NumPy array or tensor).
  *
  * Examples:
  *
  * Initializing a `Variable` with a NumPy array:**
  *
  * ```python
  * import numpy as np
  * import keras
  * initial_array = np.ones((3, 3))
  * variable_from_array = keras.Variable(initializer=initial_array)
  * ```
  *
  * Using a Keras initializer to create a `Variable`:**
  *
  * ```python
  * from keras.src.initializers import Ones
  * variable_from_initializer = keras.Variable(
  * initializer=Ones(), shape=(3, 3), dtype="float32"
  * )
  * ```
  *
  * Updating the value of a `Variable`:**
  *
  * ```python
  * new_value = np.zeros((3, 3), dtype="float32")
  * variable_from_array.assign(new_value)
  * ```
  *
  * Marking a `Variable` as non-trainable:**
  *
  * ```python
  * non_trainable_variable = keras.Variable(
  * initializer=np.ones((3, 3), dtype="float32"), trainable=False
  * )
  * ```
  */
class KerasVariable[T](var initializer: Either[T, (Array[Int], String) => T]
                    ) {
                    var shape: Option[Array[Int]] = None
                    var dtype: Option[String] = None
                    var trainable: Boolean = true
                    var autocast: Boolean = true
                    var aggregation: String = "mean"
                    var name: Option[String] = None


      def this (initializer:Either[T, (Array[Int], String) => T],  shape: Option[Array[Int]] = None,
                     dtype: Option[String] = None,
                     trainable: Boolean = true,
                     autocast: Boolean = true,
                     aggregation: String = "mean",
                     name: Option[String] = None) = {
        this(initializer)
        this.dtype = dtype
        this.trainable = trainable
        this.autocast = autocast
        this.aggregation = aggregation
        this.name = name
      } 

}
