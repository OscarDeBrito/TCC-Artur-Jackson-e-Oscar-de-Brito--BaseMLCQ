public interface Visitor {

		/**
		 * Receive notification of an HTTP method predicate.
		 * @param methods the HTTP methods that make up the predicate
		 * @see RequestPredicates#method(HttpMethod)
		 */
		void method(Set<HttpMethod> methods);

		/**
		 * Receive notification of an path predicate.
		 * @param pattern the path pattern that makes up the predicate
		 * @see RequestPredicates#path(String)
		 */
		void path(String pattern);

		/**
		 * Receive notification of an path extension predicate.
		 * @param extension the path extension that makes up the predicate
		 * @see RequestPredicates#pathExtension(String)
		 */
		void pathExtension(String extension);

		/**
		 * Receive notification of a HTTP header predicate.
		 * @param name the name of the HTTP header to check
		 * @param value the desired value of the HTTP header
		 * @see RequestPredicates#headers(Predicate)
		 * @see RequestPredicates#contentType(MediaType...)
		 * @see RequestPredicates#accept(MediaType...)
		 */
		void header(String name, String value);

		/**
		 * Receive notification of a query parameter predicate.
		 * @param name the name of the query parameter
		 * @param value the desired value of the parameter
		 * @see RequestPredicates#queryParam(String, String)
		 */
		void queryParam(String name, String value);

		/**
		 * Receive first notification of a logical AND predicate.
		 * The first subsequent notification will contain the left-hand side of the AND-predicate;
		 * followed by {@link #and()}, followed by the right-hand side, followed by {@link #endAnd()}.
		 * @see RequestPredicate#and(RequestPredicate)
		 */
		void startAnd();

		/**
		 * Receive "middle" notification of a logical AND predicate.
		 * The following notification contains the right-hand side, followed by {@link #endAnd()}.
		 * @see RequestPredicate#and(RequestPredicate)
		 */
		void and();

		/**
		 * Receive last notification of a logical AND predicate.
		 * @see RequestPredicate#and(RequestPredicate)
		 */
		void endAnd();

		/**
		 * Receive first notification of a logical OR predicate.
		 * The first subsequent notification will contain the left-hand side of the OR-predicate;
		 * the second notification contains the right-hand side, followed by {@link #endOr()}.
		 * @see RequestPredicate#or(RequestPredicate)
		 */
		void startOr();

		/**
		 * Receive "middle" notification of a logical OR predicate.
		 * The following notification contains the right-hand side, followed by {@link #endOr()}.
		 * @see RequestPredicate#or(RequestPredicate)
		 */
		void or();

		/**
		 * Receive last notification of a logical OR predicate.
		 * @see RequestPredicate#or(RequestPredicate)
		 */
		void endOr();

		/**
		 * Receive first notification of a negated predicate.
		 * The first subsequent notification will contain the negated predicated, followed
		 * by {@link #endNegate()}.
		 * @see RequestPredicate#negate()
		 */
		void startNegate();

		/**
		 * Receive last notification of a negated predicate.
		 * @see RequestPredicate#negate()
		 */
		void endNegate();

		/**
		 * Receive first notification of an unknown predicate.
		 */
		void unknown(RequestPredicate predicate);
	}
