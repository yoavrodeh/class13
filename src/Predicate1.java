public interface Predicate1<T> {

	boolean test(T t);

	static <T> Predicate1<T> isEqual1(Object targetRef) {
		return new Predicate1<T>() {
			@Override
			public boolean test(T t) {
				if (targetRef == null)
					return t == null;
				return targetRef.equals(t);
			}
		};
	}

	static <T> Predicate1<T> isEqual2(Object targetRef) {
		return (null == targetRef)
				? o -> o == null
				: o -> targetRef.equals(o);
	}

	default Predicate1<T> negate1() {
		return new Predicate1<T>() {
			@Override
			public boolean test(T t) {
				return !Predicate1.this.test(t);
			}
		};
	}

	default Predicate1<T> negate2() {
		return (t) -> !test(t);
	}

	default Predicate1<T> and1(
			Predicate1<? super T> other) {
		if (other == null)
			throw new NullPointerException();
		return new Predicate1<T>() {
			@Override
			public boolean test(T t) {
				return Predicate1.this.test(t)
						&& other.test(t);
			}
		};
	}

	default Predicate1<T> and2(
			Predicate1<? super T> other) {
		if (other == null)
			throw new NullPointerException();
		return (t) -> test(t) && other.test(t);
	}
}
