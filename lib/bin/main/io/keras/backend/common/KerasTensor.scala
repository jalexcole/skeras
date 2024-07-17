package io.keras.backend.common;
/** Symbolic tensor -- encapsulates a shape and a dtype.
  *
  * You can use `KerasTensor` instances to build computation graphs of Keras
  * operations, such as `keras.Function` objects or Functional
  * `keras.models.Model` objects.
  *
  * Example:
  *
  * >>> x = keras.KerasTensor(shape=(3, 4), dtype="float32") >>> x.shape (3, 4)
  * >>> x.dtype float32
  *
  * Calling a Keras operation (including a layer or a model) on a `KerasTensor`
  * instance will return another `KerasTensor` instance with the appropriate 
  * shape and dtype. This is called a "symbolic call" (since there is no actual
  * data involved). The computation of the correct output shape and dtype is
  * called "static shape inference".
  */
class KerasTensor(
    val shape: Object,
    val dtype: String = "float32",
    val sparse: Boolean = false,
    val recordHistory: Boolean = true,
    val name: String = null
) {
   
}
