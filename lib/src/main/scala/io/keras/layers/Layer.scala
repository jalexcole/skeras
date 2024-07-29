package io.keras.layers

import io.keras.ops.Operation;
import io.keras.savings.KerasSaveable;
import org.tensorflow.Tensor
import io.keras.losses.Loss


type BackendLayer;

/**
  * This is the class from which all layers inherit.

    A layer is a callable object that takes as input one or more tensors and
    that outputs one or more tensors. It involves *computation*, defined
    in the `call()` method, and a *state* (weight variables). State can be
    created:

    * in `__init__()`, for instance via `self.add_weight()`;
    * in the optional `build()` method, which is invoked by the first
      `__call__()` to the layer, and supplies the shape(s) of the input(s),
      which may not have been known at initialization time.

    Layers are recursively composable: If you assign a Layer instance as an
    attribute of another Layer, the outer layer will start tracking the weights
    created by the inner layer. Nested layers should be instantiated in the
    `__init__()` method or `build()` method.

    Users will just instantiate a layer and then treat it as a callable.

    Args:
    @param    trainable: Boolean, whether the layer's variables should be trainable.
        name: String name of the layer.
        dtype: The dtype of the layer's computations and weights. Can also be a
            `keras.DTypePolicy`,
            which allows the computation and
            weight dtype to differ. Defaults to `None`. `None` means to use
            `keras.config.dtype_policy()`,
            which is a `float32` policy unless set to different value
            (via `keras.config.set_dtype_policy()`).

    Attributes:
        name: The name of the layer (string).
        dtype: Dtype of the layer's weights. Alias of `layer.variable_dtype`.
        variable_dtype: Dtype of the layer's weights.
        compute_dtype: The dtype of the layer's computations.
            Layers automatically cast inputs to this dtype, which causes
            the computations and output to also be in this dtype.
            When mixed precision is used with a
            `keras.DTypePolicy`, this will be different
            than `variable_dtype`.
        trainable_weights: List of variables to be included in backprop.
        non_trainable_weights: List of variables that should not be
            included in backprop.
        weights: The concatenation of the lists trainable_weights and
            non_trainable_weights (in this order).
        trainable: Whether the layer should be trained (boolean), i.e.
            whether its potentially-trainable weights should be returned
            as part of `layer.trainable_weights`.
        input_spec: Optional (list of) `InputSpec` object(s) specifying the
            constraints on inputs that can be accepted by the layer.

    We recommend that descendants of `Layer` implement the following methods:

    * `__init__()`: Defines custom layer attributes, and creates layer weights
        that do not depend on input shapes, using `add_weight()`,
        or other state.
    * `build(self, input_shape)`: This method can be used to create weights that
        depend on the shape(s) of the input(s), using `add_weight()`, or other
        state. `__call__()` will automatically build the layer
        (if it has not been built yet) by calling `build()`.
    * `call(self, *args, **kwargs)`: Called in `__call__` after making
        sure `build()` has been called. `call()` performs the logic of applying
        the layer to the input arguments.
        Two reserved keyword arguments you can optionally use in `call()` are:
            1. `training` (boolean, whether the call is in inference mode or
                training mode).
            2. `mask` (boolean tensor encoding masked timesteps in the input,
                used e.g. in RNN layers).
        A typical signature for this method is `call(self, inputs)`, and user
        could optionally add `training` and `mask` if the layer need them.
    * `get_config(self)`: Returns a dictionary containing the configuration
        used to initialize this layer. If the keys differ from the arguments
        in `__init__()`, then override `from_config(self)` as well.
        This method is used when saving
        the layer or a model that contains this layer.

    Examples:

    Here's a basic example: a layer with two variables, `w` and `b`,
    that returns `y = w . x + b`.
    It shows how to implement `build()` and `call()`.
    Variables set as attributes of a layer are tracked as weights
    of the layers (in `layer.weights`).

    {{{python
    class SimpleDense(Layer):
        def __init__(self, units=32):
            super().__init__()
            self.units = units

        # Create the state of the layer (weights)
        def build(self, input_shape):
            self.kernel = self.add_weight(
                shape=(input_shape[-1], self.units),
                initializer="glorot_uniform",
                trainable=True,
                name="kernel",
            )
            self.bias = self.add_weight(
                shape=(self.units,),
                initializer="zeros",
                trainable=True,
                name="bias",
            )

        # Defines the computation
        def call(self, inputs):
            return ops.matmul(inputs, self.kernel) + self.bias

    # Instantiates the layer.
    linear_layer = SimpleDense(4)

    # This will also call `build(input_shape)` and create the weights.
    y = linear_layer(ops.ones((2, 2)))
    assert len(linear_layer.weights) == 2

    # These weights are trainable, so they're listed in `trainable_weights`:
    assert len(linear_layer.trainable_weights) == 2
}}}

    Besides trainable weights, updated via backpropagation during training,
    layers can also have non-trainable weights. These weights are meant to
    be updated manually during `call()`. Here's a example layer that computes
    the running sum of its inputs:

    {{{python
    class ComputeSum(Layer):

      def __init__(self, input_dim):
          super(ComputeSum, self).__init__()
          # Create a non-trainable weight.
          self.total = self.add_weight(
            shape=(),
            initializer="zeros",
            trainable=False,
            name="total",
          )

      def call(self, inputs):
          self.total.assign(self.total + ops.sum(inputs))
          return self.total

    my_sum = ComputeSum(2)
    x = ops.ones((2, 2))
    y = my_sum(x)

    assert my_sum.weights == [my_sum.total]
    assert my_sum.non_trainable_weights == [my_sum.total]
    assert my_sum.trainable_weights == []
    }}}
  */
abstract class Layer {
    /**
      * The name of the layer (string).
      */
    var  name: String = ""
    /**
      * Dtype of the layer's weights. Alias of `layer.variable_dtype`.
      */
    var dtype: Option[String] = None
    /**
     * Dtype of the layer's weights.
     */
    var variable_dtype: String = "float32"
    /**
      * The dtype of the layer's computations.
      * Layers automatically cast inputs to this dtype, which causes
        the computations and output to also be in this dtype.
        When mixed precision is used with a
        `keras.DTypePolicy`, this will be different
        than `variable_dtype`.
      */
    var compute_dtype: Object = Object()
        /**
          * List of variables to be included in backprop.
          */
    var trainable_weights: Object = Object()
        /**
          * List of variables that should not be
            included in backprop.
          */
    var non_trainable_weights: Object = Object()
        /**
          * The concatenation of the lists trainable_weights and
            non_trainable_weights (in this order).
          */
    var weights: List[Object] = List()
        /**
          * Whether the layer should be trained (boolean), i.e.
            whether its potentially-trainable weights should be returned
            as part of `layer.trainable_weights`.
          */
    var trainable: Boolean = true
        /**
         * Optional (list of) `InputSpec` object(s) specifying the
            constraints on inputs that can be accepted by the layer.
         */
    var input_spec: List[Object] = List()

    init()

    def this(
        trainable: Boolean=true,
        dtype: Option[String]=None,
        autocast: Boolean=true,
        name: Option[String]=None) = {
            this()
            this.trainable = trainable
            this.dtype = dtype
            this.name = name.getOrElse("")
        }

    private def init() = {

    }

    private def initializeTracker() = {
        
    }

    def build(inputShape: Array[Int]): Unit;

    def call(inputs: Tensor): Tensor;

   

    def path = {

    }

    def inputSpec() = {

    }

    private def lockState() = {

    }

    def addVariable() = {

    }

    def addWeight() = {

    }

    

    def trainableWeights() = {

    }

    def metrics() = {

    }

    def quantizationMode() = {

    }
    /**
      * Can be called inside of the `call()` method to add a scalar loss.

        Example:

        ```python
        class MyLayer(Layer):
            ...
            def call(self, x):
                self.add_loss(ops.sum(x))
                return x
        ```
      */
    def addLoss(loss: Loss) = {

    }
}


