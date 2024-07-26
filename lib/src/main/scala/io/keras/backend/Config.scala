package io.keras.backend

 object Config {
    /**
      * Return the default float type, as a string.

    E.g. `'bfloat16'`, `'float16'`, `'float32'`, `'float64'`.

    Returns:
        String, the current default float type.

    Example:

    >>> keras.config.floatx()
    'float32'
      */
    def floatx() = {

    }  

    /**
      * Return the value of the fuzz factor used in numeric expressions.

      * @returns: A float.

    Example:
    {{{
    >>> keras.config.epsilon()
    1e-07
}}}
      */
    def epsilon() = {

    }

    def imageDataFormat() = {

    }

    /**
      * Publicly accessible method for determining the current backend.

    Returns:
        String, the name of the backend Keras is currently using. One of
            `"tensorflow"`, `"torch"`, or `"jax"`.

    Example:
    {{{
    >>> keras.config.backend()
    'tensorflow' }}}
      */
    def backend() = {

    }


 }
