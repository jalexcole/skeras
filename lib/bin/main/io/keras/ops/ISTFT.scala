package io.keras.ops

class ISTFT extends Operation {
  
}

object ISTFT {
    /**
      * Inverse Short-Time Fourier Transform along the last axis of the input.

    To reconstruct an original waveform, the parameters should be the same in
    `stft`.

    Args:
        x: Tuple of the real and imaginary parts of the input tensor. Both
            tensors in the tuple should be of floating type.
        sequence_length: An integer representing the sequence length.
        sequence_stride: An integer representing the sequence hop size.
        fft_length: An integer representing the size of the FFT that produced
            `stft`.
        length: An integer representing the output is clipped to exactly length.
            If not specified, no padding or clipping take place. Defaults to
            `None`.
        window: A string, a tensor of the window or `None`. If `window` is a
            string, available values are `"hann"` and `"hamming"`. If `window`
            is a tensor, it will be used directly as the window and its length
            must be `sequence_length`. If `window` is `None`, no windowing is
            used. Defaults to `"hann"`.
        center: Whether `x` was padded on both sides so that the t-th sequence
            is centered at time `t * sequence_stride`. Defaults to `True`.

    Returns:
        A tensor containing the inverse Short-Time Fourier Transform along the
        last axis of `x`.

    Example:

    >>> x = keras.ops.convert_to_tensor([0.0, 1.0, 2.0, 3.0, 4.0])
    >>> istft(stft(x, 1, 1, 1), 1, 1, 1)
    array([0.0, 1.0, 2.0, 3.0, 4.0])
    
      */
    def istft() = {

    }
}
