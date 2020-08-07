package VariadicFunction;

import java.util.Arrays;
import java.util.function.Function;

public class InputConverter<T>
{
	T first;

	InputConverter(T first)
	{
		this.first = first;
	}

	<R> R convertBy(Function... f)
	{
		int last = f.length - 1;
		if(last == 0) return (R) f[last].apply(first);
		return (R) f[last].apply(convertBy(Arrays.copyOf(f, last)));
	}
}
