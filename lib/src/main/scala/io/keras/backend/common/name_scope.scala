package io.keras.backend.common

/** Creates a sub-namespace for variable paths.
  *
  * Args:
  * @param name:
  *   Name of the current scope (string).
  * @param caller:
  *   Optional ID of a caller object (e.g. class instance).
  * @param deduplicate:
  *   If `True`, if `caller` was passed, and the previous caller matches the
  *   current caller, and the previous name matches the current name, do not
  *   reenter a new namespace. override_parent: Can be used to provide an
  *   absolute path which would override any previously opened name scopes.
  */
class name_scope {}
