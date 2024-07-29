package io.keras.backend.tensorflow

import org.tensorflow.Tensor
import org.tensorflow.proto.TrackableObjectGraphOuterClass.TrackableObjectGraph.TrackableObject
import io.keras.backend.common.KerasVariable

class Variable  {
    def this(value: Object) = {
        this()
    }

    

    private def initialize() = {

    }

    private def defferedInitialize() = {

    }

    private def directAssign() = {

    }

    private def convertToTensor() = {}
    def numpy() = {}
    def shape() = {}
    private def tfTensor() = {}
    private def sharedName() = {}
    private def serializeToTensor() = {}
    private def restoreFromTensors() = {}
    private def copyTrackableToCPU() = {}
    private def exportToSavedModelGraph() = {}
    private def writeObjectProto() = {}

}