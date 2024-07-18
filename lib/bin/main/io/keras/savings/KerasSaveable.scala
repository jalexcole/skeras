package io.keras.savings

import io.keras.models.Model

import java.io.{ByteArrayInputStream, ByteArrayOutputStream}
import scala.util.Try

trait KerasSaveable {
  // Note: renaming this function will cause old pickles to be broken.
  // This is probably not a huge deal, as pickle should not be a recommended
  // saving format -- it should only be supported for use with distributed
  // computing frameworks.

  @throws(classOf[NotImplementedError])
  def objType(): Unit = {
    throw new NotImplementedError("KerasSaveable subclasses must provide an implementation for `objType()`")
  }

  def unpickleModel(bytesStream: ByteArrayInputStream): Try[Model] = {
    Try {
      val savingLib = Class.forName("keras.src.saving.saving_lib")
      val loadModelMethod = savingLib.getMethod("_load_model_from_fileobj", classOf[ByteArrayInputStream], classOf[AnyRef], classOf[Boolean], classOf[Boolean])
      loadModelMethod.invoke(null, bytesStream, null, java.lang.Boolean.TRUE, java.lang.Boolean.FALSE).asInstanceOf[Model]
    }
  }

  @throws(classOf[Exception])
  def reduce(): (ByteArrayInputStream => Try[Model], ByteArrayInputStream) = {
    val savingLib = Class.forName("keras.src.saving.saving_lib")
    val saveModelMethod = savingLib.getMethod("_save_model_to_fileobj", classOf[Any], classOf[ByteArrayOutputStream], classOf[String])
    val buf = new ByteArrayOutputStream()
    saveModelMethod.invoke(null, this, buf, "h5")
    (unpickleModel _, new ByteArrayInputStream(buf.toByteArray))
  }
}
