package io.keras.callbacks

import io.keras.models.Model

trait Callback {
  var model: Option[Model] = None;
  var validationData: Option[Object] = None;

  /**
   * A backwards compatibility alias for `on_train_batch_begin`
   * @param batch
   * @param logs
   *
   */
  def on_batch_begin( batch: Object, logs: Object = None): Unit;


  /**
   *  backwards compatibility alias for `on_train_batch_end`.
   * @param batch
   * @param logs
   */
  def on_batch_end( batch: Object, logs: Object = None): Unit;

  /**
   * Called at the start of an epoch.
   *
   * Subclasses should override for any actions to run. This function should
   * only be called during TRAIN mode.
   *
   * Args:
   * epoch: Integer, index of epoch.
   * logs: Dict. Currently no data is passed to this argument for this
   * method but that may change in the future.
   *
   * @param self
   * @param epoch
   * @param logs
   */
  def on_epoch_begin(epoch: Object, logs: Object = None): Unit;

  /**
   * Called at the end of an epoch.
   *
   * Subclasses should override for any actions to run. This function should
   * only be called during TRAIN mode.
   *
   * Args:
   * epoch: Integer, index of epoch.
   * logs: Dict, metric results for this training epoch, and for the
   * validation epoch if validation is performed. Validation result
   * keys are prefixed with `val_`. For training epoch, the values of
   * the `Model`'s metrics are returned. Example:
   * `{'loss': 0.2, 'accuracy': 0.7}`.
   *
   * @param self
   * @param epoch
   * @param logs
   */
  def on_epoch_end(epoch: Object, logs: Object = None): Unit;

  /**
   * Called at the beginning of a training batch in `fit` methods.
   *
   * Subclasses should override for any actions to run.
   *
   * Note that if the `steps_per_execution` argument to `compile` in
   * `Model` is set to `N`, this method will only be called every
   * `N` batches.
   *
   * Args:
   * batch: Integer, index of batch within the current epoch.
   * logs: Dict. Currently no data is passed to this argument for this
   * method but that may change in the future.
   *
   * @param batch
   * @param logs
   */
  def on_train_batch_begin(batch: Object, logs: Object = None): Unit;


//  #For backwards compatibility.
//    self.on_batch_begin(batch, logs = logs)

  /**
   * Called at the end of a training batch in `fit` methods.
   *
   * Subclasses should override for any actions to run.
   *
   * Note that if the `steps_per_execution` argument to `compile` in
   * `Model` is set to `N`, this method will only be called every
   * `N` batches.
   *
   * Args:
   * batch: Integer, index of batch within the current epoch.
   * logs: Dict. Aggregated metric results up until this batch.
   *
   * @param batch
   * @param logs
   */
  def on_train_batch_end( batch: Object, logs: Object = None): Unit;


//  #For backwards compatibility.
//    self.on_batch_end(batch, logs = logs)

  /**
   * Called at the beginning of a batch in `evaluate` methods.
   *
   * Also called at the beginning of a validation batch in the `fit`
   * methods, if validation data is provided.
   *
   * Subclasses should override for any actions to run.
   *
   * Note that if the `steps_per_execution` argument to `compile` in
   * `Model` is set to `N`, this method will only be called every
   * `N` batches.
   *
   * Args:
   * batch: Integer, index of batch within the current epoch.
   * logs: Dict. Currently no data is passed to this argument for this
   * method but that may change in the future.
   *
   * @param batch
   * @param logs
   */
  def on_test_batch_begin(batch: Object, logs: Object = None): Unit;


  /**
   *
   * @param self
   * @param batch
   * @param logs
   *
   * Called at the end of a batch in `evaluate` methods.
   *
   *  Also called at the end of a validation batch in the `fit`
   *  methods, if validation data is provided.
   *
   *  Subclasses should override for any actions to run.
   *
   *  Note that if the `steps_per_execution` argument to `compile` in
   *  `Model` is set to `N`, this method will only be called every
   *  `N` batches.
   *
   *  Args:
   *  batch: Integer, index of batch within the current epoch.
   *  logs: Dict. Aggregated metric results up until this batch.
   */
  def on_test_batch_end(batch: Object, logs: Object = None): Unit;


  /**
   * Called at the beginning of a batch in `predict` methods.
   *
   * Subclasses should override for any actions to run.
   *
   * Note that if the `steps_per_execution` argument to `compile` in
   * `Model` is set to `N`, this method will only be called every
   * `N` batches.
   *
   * Args:
   * batch: Integer, index of batch within the current epoch.
   * logs: Dict. Currently no data is passed to this argument for this
   * method but that may change in the future.
   *
   * @param batch
   * @param logs
   */
  def on_predict_batch_begin(batch: Object, logs: Object = None): Unit;


  /**
   * Called at the end of a batch in `predict` methods.
   *
   * Subclasses should override for any actions to run.
   *
   * Note that if the `steps_per_execution` argument to `compile` in
   * `Model` is set to `N`, this method will only be called every
   * `N` batches.
   *
   * Args:
   * batch: Integer, index of batch within the current epoch.
   * logs: Dict. Aggregated metric results up until this batch.
   *
   * @param batch
   * @param logs
   */
  def on_predict_batch_end(batch: Object, logs: Object = None): Unit;


  /**
   * Called at the beginning of training.
   *
   * Subclasses should override for any actions to run.
   *
   * Args:
   * logs: Dict. Currently no data is passed to this argument for this
   * method but that may change in the future.
   *
   * @param logs
   */
  def on_train_begin(logs: Object = None): Unit;


  /**
   * Called at the end of training.
   *
   * Subclasses should override for any actions to run.
   *
   * Args:
   * logs: Dict. Currently the output of the last call to
   * `on_epoch_end()` is passed to this argument for this method but
   * that may change in the future.
   *
   * @param logs
   */
  def on_train_end(logs: Object = None): Unit;


  /**
   * Called at the beginning of evaluation or validation.
   *
   * Subclasses should override for any actions to run.
   *
   * Args:
   * logs: Dict. Currently no data is passed to this argument for this
   * method but that may change in the future.
   *
   * @param logs
   */
  def on_test_begin(logs: Object = None): Unit;


  /**
   * Called at the end of evaluation or validation.
   *
   * Subclasses should override for any actions to run.
   *
   * Args:
   * logs: Dict. Currently the output of the last call to
   * `on_test_batch_end()` is passed to this argument for this method
   * but that may change in the future.
   *
   * @param logs
   */
  def on_test_end(logs: Object = None): Unit;


  /**
   * Called at the beginning of prediction.
   *
   * Subclasses should override for any actions to run.
   *
   * Args:
   * logs: Dict. Currently no data is passed to this argument for this
   * method but that may change in the future.
   *
   * @param logs
   */
  def on_predict_begin( logs: Object = None): Unit;


  /**
   * Called at the end of prediction.
   *
   * Subclasses should override for any actions to run.
   *
   * Args:
   * logs: Dict. Currently no data is passed to this argument for this
   * method but that may change in the future.
   *
   * @param logs
   */
  def on_predict_end(logs: Option[Map[Object, Object]] = None): Unit;

}
