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
class KerasTensor(val shape: List[Int]) {
    var dtype: String = "float32"
    var sparse: Boolean = false
    var recordHistory: Boolean = true
    var name: String = null

  def this (shape: List[Int], dtype: String = "float32",
    sparse: Boolean = false,
    recordHistory: Boolean = true,
    name: String = null) = {
    this(shape)
    this.dtype = dtype
    this.sparse = sparse
    this.recordHistory = recordHistory
    this.name = name
    }

    // def dtype(): String ={this.dtype}
    // def shape(): List[Int] = {this.shape}

}
