package io.keras.trainers

/** Configures the model for training.
  *
  * Example:
  *
  * ```python
  * model.compile(
  * optimizer=keras.optimizers.Adam(learning_rate=1e-3),
  * loss=keras.losses.BinaryCrossentropy(),
  * metrics=[
  * keras.metrics.BinaryAccuracy(),
  * keras.metrics.FalseNegatives(),
  * ],
  * )
  * ```
  *
  * Args: optimizer: String (name of optimizer) or optimizer instance. See
  * `keras.optimizers`. loss: Loss function. May be a string (name of loss
  * function), or a `keras.losses.Loss` instance. See `keras.losses`. A loss
  * function is any callable with the signature `loss = fn(y_true, y_pred)`,
  * where `y_true` are the ground truth values, and `y_pred` are the model's
  * predictions. `y_true` should have shape `(batch_size, d0, .. dN)` (except in
  * the case of sparse loss functions such as sparse categorical crossentropy
  * which expects integer arrays of shape `(batch_size, d0, .. dN-1)`). `y_pred`
  * should have shape `(batch_size, d0, .. dN)`. The loss function should return
  * a float tensor. loss_weights: Optional list or dictionary specifying scalar
  * coefficients (Python floats) to weight the loss contributions of different
  * model outputs. The loss value that will be minimized by the model will then
  * be the *weighted sum* of all individual losses, weighted by the
  * `loss_weights` coefficients. If a list, it is expected to have a 1:1 mapping
  * to the model's outputs. If a dict, it is expected to map output names
  * (strings) to scalar coefficients. metrics: List of metrics to be evaluated
  * by the model during training and testing. Each of this can be a string (name
  * of a built-in function), function or a `keras.metrics.Metric` instance. See
  * `keras.metrics`. Typically you will use `metrics=['accuracy']`. A function
  * is any callable with the signature `result = fn(y_true, _pred)`. To specify
  * different metrics for different outputs of a multi-output model, you could
  * also pass a dictionary, such as `metrics={'a':'accuracy', 'b':['accuracy',
  * 'mse']}`. You can also pass a list to specify a metric or a list of metrics
  * for each output, such as `metrics=[['accuracy'], ['accuracy', 'mse']]` or
  * `metrics=['accuracy', ['accuracy', 'mse']]`. When you pass the strings
  * 'accuracy' or 'acc', we convert this to one of
  * `keras.metrics.BinaryAccuracy`, `keras.metrics.CategoricalAccuracy`,
  * `keras.metrics.SparseCategoricalAccuracy` based on the shapes of the targets
  * and of the model output. A similar conversion is done for the strings
  * `"crossentropy"` and `"ce"` as well. The metrics passed here are evaluated
  * without sample weighting; if you would like sample weighting to apply, you
  * can specify your metrics via the `weighted_metrics` argument instead.
  * weighted_metrics: List of metrics to be evaluated and weighted by
  * `sample_weight` or `class_weight` during training and testing. run_eagerly:
  * Bool. If `True`, this model's forward pass will never be compiled. It is
  * recommended to leave this as `False` when training (for best performance),
  * and to set it to `True` when debugging. steps_per_execution: Int. The number
  * of batches to run during each a single compiled function call. Running
  * multiple batches inside a single compiled function call can greatly improve
  * performance on TPUs or small models with a large Python overhead. At most,
  * one full epoch will be run each execution. If a number larger than the size
  * of the epoch is passed, the execution will be truncated to the size of the
  * epoch. Note that if `steps_per_execution` is set to `N`,
  * `Callback.on_batch_begin` and `Callback.on_batch_end` methods will only be
  * called every `N` batches (i.e. before/after each compiled function
  * execution). Not supported with the PyTorch backend. jit_compile: Bool or
  * `"auto"`. Whether to use XLA compilation when compiling a model. For `jax`
  * and `tensorflow` backends, `jit_compile="auto"` enables XLA compilation if
  * the model supports it, and disabled otherwise. For `torch` backend, `"auto"`
  * will default to eager execution and `jit_compile=True` will run with
  * `torch.compile` with the `"inductor"` backend. auto_scale_loss: Bool. If
  * `True` and the model dtype policy is `"mixed_float16"`, the passed optimizer
  * will be automatically wrapped in a `LossScaleOptimizer`, which will
  * dynamically scale the loss to prevent underflow.
  */
class Trainer {}
