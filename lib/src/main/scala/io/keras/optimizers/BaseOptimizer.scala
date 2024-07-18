package io.keras.optimizers

abstract class BaseOptimizer {
  def build(): Unit = {

  }

  protected def varKey(): Unit = {

  }

  def addVariables(): Unit = {

  }

  def assign(): Unit = {

  }

  def assignAdd(): Unit = {

  }

  /**
   * Apply method that can be overridden by different backends.
   *
   * JAX overrides it in order to deal with statelessness in gradient
   * accumulation and EMA handling.
   *
   * The below implementation is intended to be generally backend-agnostic,
   * but may not work with all backends.
   *
   * This method does 4 things:
   * - Call the optimizer's update_step() to update trainable variables
   * and optimizer variables.
   * - Update EMA variables, if EMA is configured.
   * - Update gradient accumulators, if gradient accumulation is configured.
   * - Update the iteration counter.
   */
  protected def backendApplyGradients(): Unit = {

  }

  def statelessApply(): Unit = {

  }

  def scaleLoss(): Unit = {

  }

  def learningRate(): Unit = {

  }



}
