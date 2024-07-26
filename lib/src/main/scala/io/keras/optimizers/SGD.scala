package io.keras.optimizers

/**
  * Gradient descent (with momentum) optimizer.

    Update rule for parameter `w` with gradient `g` when `momentum` is 0:

    ```python
    w = w - learning_rate * g
    ```

    Update rule when `momentum` is larger than 0:

    ```python
    velocity = momentum * velocity - learning_rate * g
    w = w + velocity
    ```

    When `nesterov=True`, this rule becomes:

    ```python
    velocity = momentum * velocity - learning_rate * g
    w = w + momentum * velocity - learning_rate * g
    ```

    Args:
        learning_rate: A float, a
            `keras.optimizers.schedules.LearningRateSchedule` instance, or
            a callable that takes no arguments and returns the actual value to
            use. The learning rate. Defaults to `0.01`.
        momentum: float hyperparameter >= 0 that accelerates gradient descent in
            the relevant direction and dampens oscillations. 0 is vanilla
            gradient descent. Defaults to `0.0`.
        nesterov: boolean. Whether to apply Nesterov momentum.
            Defaults to `False`.
        {{base_optimizer_keyword_args}}
  */
trait SGD {

}