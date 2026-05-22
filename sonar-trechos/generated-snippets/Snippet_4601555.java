public interface Float2ObjFunction<T> extends Serializable {
  /**
   * Returns the result of applying this function to given {@code input}.
   *
   * The returned object may or may not be a new instance,
   * depending on the implementation.
   *
   * @param input input
   * @return result
   */
  T apply(float input);
}
