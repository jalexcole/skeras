package io.keras.backend.tensorflow

import io.keras.backend.common.KerasVariable

object core {
  var supportsSparseTensors: Boolean = true

  def convertToTensor() = {}
  def convertToNumpy() = {}
  def isTensor(x: Object) = {}
  def shape(x: Object) = {}
  def cast() = {}
  def computeOutputSpec() = {}
  def cond() = {}
  def vectorizedMap() = {}
  def map() = {}
  def scan = {}
  def associativeScan() = {}
  def scatter() = {}
  def scatterUpdate() = {}
  def slice() = {}
  def sliceUpdate() = {}
  def switch() = {}
  def whileLoop() = {}
  def foriLoop() = {}
  def stopGradient() = {}
  def unstack() = {}
  def randomSeedDtype(): String = {return "int64"}
  def customGradient() = {}
  def deviceScope() = {}
}



class NameScope {
    def initializer() = {}

    
}
