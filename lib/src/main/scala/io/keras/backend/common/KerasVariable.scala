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
class KerasVariable(var initializer: Either[T, (Array[Int], String) => T],
                    var shape: Option[Array[Int]] = None,
                    var dtype: Option[String] = None,
                    var trainable: Boolean = true,
                    var autocast: Boolean = true,
                    var aggregation: String = "mean",
                    var name: Option[String] = None) {

  name = name.orElse(Some(autoName(this.getClass.getSimpleName)))

  if (!name.get.matches("^[^/]+$")) {
    throw new IllegalArgumentException(s"Argument `name` must be a string and cannot contain character `/`. Received: name=${name.get}")
  }

  if (!Set("mean", "sum", "only_first_replica").contains(aggregation)) {
    throw new IllegalArgumentException(s"Invalid value for argument `aggregation`. Expected one of {'mean', 'sum', 'only_first_replica'}. Received: aggregation=$aggregation")
  }

  var path: String = {
    val parentPath = currentPath()
    if (parentPath.nonEmpty) s"$parentPath/${name.get}" else name.get
  }

  dtype = Some(standardizeDtype(dtype))
  var _shape: Option[Array[Int]] = None
  var _initializer: Option[(Array[Int], String) => T] = None
  var _regularizer: Option[Any] = None
  var _constraint: Option[Any] = None
  var _trainable: Boolean = trainable
  var _autocast: Boolean = autocast
  var _aggregation: String = aggregation
  var _overwriteWithGradient: Boolean = false

  initializer match {
    case Left(initValue) => _initialize(initValue)
    case Right(initFunc) =>
      if (shape.isEmpty) {
        throw new IllegalArgumentException(
          s"When creating a Variable from an initializer, the `shape` argument should be specified. Received: initializer=$initFunc and shape=$shape"
        )
      }
      _initializer = Some(initFunc)
      _shape = Some(_validateShape(shape.get))
      if (inStatelessScope()) {
        registerUninitializedVariable(this)
      } else {
        val value = initFunc(_shape.get, dtype.get)
        _initialize(value)
      }
  }

  var _ndim: Int = _shape.map(_.length).getOrElse(0)

  private def _initialize(value: T): Unit = {
    // Initialize the variable with the given value
  }

  private def _validateShape(shape: Array[Int]): Array[Int] = {
    val standardizedShape = standardizeShape(shape)
    if (standardizedShape.contains(null)) {
      throw new IllegalArgumentException(s"Shapes used to initialize variables must be fully-defined (no `None` dimensions). Received: shape=$shape for variable path='$path'")
    }
    standardizedShape
  }

  def numpy(): Array[Byte] = {
    // Convert the variable to a NumPy array
    Array[Byte]()
  }

  def assign(value: T): Unit = {
    val convertedValue = _convertToTensor(value, dtype)
    if (!shapeEqual(convertedValue.shape, _shape.get)) {
      throw new IllegalArgumentException(
        s"The shape of the target variable and the shape of the target value in `variable.assign(value)` must match. variable.shape=${_shape.get}, Received: value.shape=${convertedValue.shape}. Target variable: $this"
      )
    }
    if (inStatelessScope()) {
      getStatelessScope().addUpdate((this, value))
    } else {
      _directAssign(value)
    }
  }

  def assignAdd(value: T): Unit = assign(add(this.value, value))

  def assignSub(value: T): Unit = assign(sub(this.value, value))

  def _convertToTensor(value: T, dtype: Option[String]): T = {
    // Convert the value to a tensor
    value
  }

  def _directAssign(value: T): Unit = {
    // Directly assign the value to the variable
  }

  override def toString: String = s"<KerasVariable shape=${_shape.get}, dtype=$dtype, path=$path>"

  def value: T = {
    if (inStatelessScope()) {
      val scope = getStatelessScope()
      val currentValue = scope.getCurrentValue(this)
      if (currentValue.isDefined) {
        return _maybeAutocast(currentValue.get)
      }
    }
    _initializer match {
      case Some(initFunc) => _maybeAutocast(initFunc(_shape.get, dtype.get))
      case None => throw new IllegalStateException("Variable not initialized")
    }
  }

//  private def _maybeAutocast(value: T): T = {
//    // Handle autocasting if needed
//    value
//  }
}

//object KerasVariable {
//  def apply[T: ClassTag](
//                          initializer: Either[T, (Array[Int], String) => T],
//                          shape: Option[Array[Int]] = None,
//                          dtype: Option[String] = None,
//                          trainable: Boolean = true,
//                          autocast: Boolean = true,
//                          aggregation: String = "mean",
//                          name: Option[String] = None
//                        ): KerasVariable[T] = new KerasVariable[T](initializer, shape, dtype, trainable, autocast, aggregation, name)
//}
