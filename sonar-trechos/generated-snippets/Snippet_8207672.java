@SuppressWarnings("unused")
  public final class RightProjection extends AbstractProjection<R, L> implements Projection<R, L, L, R> {
    private RightProjection() {}

    @Override
	public R get() {
      return getRight();
    }

    @Override
	public boolean isDefined() {
      return isRight();
    }

    @Override
	public R on(final Function<? super L, ? extends R> f) {
      return isRight() ? get() : f.apply(left().get());
    }

    //
    // definitions that can't be shared without higher-kinded types
    //

    /**
     * Map the given function across this projection's value if it has one.
     * 
     * @param <X> the RHS type
     * @param f The function to map across this projection.
     * @return A new either value after mapping.
     */
    public <X> Either<L, X> map(final Function<? super R, X> f) {
      return isRight() ? new Right<L, X>(f.apply(get())) : this.<X> toLeft();
    }

    /**
     * Binds the given function across this projection's value if it has one.
     * 
     * @param <X> the RHS type
     * @param f The function to bind across this projection.
     * @return A new either value after binding.
     */
    public <X> Either<L, X> flatMap(final Function<? super R, Either<L, X>> f) {
      return isRight() ? f.apply(get()) : this.<X> toLeft();
    }

    <X> Left<L, X> toLeft() {
      return new Left<L, X>(left().get());
    }

    /**
     * Anonymous bind through this projection.
     * 
     * @param <X> the RHS type
     * @param e The value to bind with.
     * @return An either after binding through this projection.
     */
    public <X> Either<L, X> sequence(final Either<L, X> e) {
      return flatMap(Functions.<Either<L, X>> constant(e));
    }

    /**
     * Returns <code>None</code> if this projection has no value or if the given
     * predicate <code>p</code> does not hold for the value, otherwise, returns
     * a left in <code>Some</code>.
     * 
     * @param <X> the LHS type
     * @param f The predicate function to test on this projection's value.
     * @return <code>None</code> if this projection has no value or if the given
     * predicate <code>p</code> does not hold for the value, otherwise, returns
     * a left in <code>Some</code>.
     */
    public <X> Optional<Either<X, R>> filter(final Predicate<? super R> f) {
      if (isRight() && f.apply(get())) {
        final Either<X, R> result = new Right<X, R>(get());
        return Optional.of(result);
      }
      return Optional.absent();
    }

    /**
     * Function application on this projection's value.
     * 
     * @param <X> the RHS type
     * @param either The either of the function to apply on this projection's
     * value.
     * @return The result of function application within either.
     */
    public <X> Either<L, X> apply(final Either<L, Function<R, X>> either) {
      return either.right().flatMap(new Function<Function<R, X>, Either<L, X>>() {
        @Override
		public Either<L, X> apply(final Function<R, X> f) {
          return map(f);
        }
      });
    }

    /**
     * Coerces our left type as X. Dangerous, isRight() must be true
     * 
     * @param <X> the type to coerce to.
     * @return an either with the coerced left type.
     */
    <X> Either<X, R> as() {
      return right(get());
    }
  }
