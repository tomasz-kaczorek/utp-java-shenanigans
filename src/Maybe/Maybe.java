package Maybe;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe<T>
{
	T x;

	Maybe(T x)
	{
		this.x = x;
	}

	static <T> Maybe<T> of(T x)
	{
		return new Maybe(x);
	}

	void ifPresent(Consumer cons)
	{
		if (x != null) cons.accept(x);
	}

	<R> Maybe<R> map(Function<T, R> func)
	{
		if(x != null) return new Maybe(func.apply(x));
		return new Maybe(null);
	}

	T get() throws NoSuchElementException
	{
		if(x != null) return x;
		throw new NoSuchElementException(" maybe is empty");
	}

	boolean isPresent()
	{
		return x != null;
	}

	T orElse(T defVal)
	{
		if(x != null) return x;
		return defVal;
	}

	Maybe<T> filter(Predicate<T> pred)
	{
		if(x == null || pred.test(x)) return this;
		return new Maybe(null);
	}

	public String toString()
	{
		if(x != null) return "Maybe has value " + x;
		return "Maybe is empty";
	}
}
