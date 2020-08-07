package ExtendedList;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class XList<T> extends ArrayList<T>
{
	XList(T... t)
	{
		super(Arrays.asList(t));
	}

	XList(Collection<T> c)
	{
		super(c);
	}

	static <T> XList<T> of(T... t)
	{
		return new XList<>(t);
	}

	static <T> XList<T> of(Collection<T> i)
	{
		return new XList<>(i);
	}

	static XList<String> ofChars(String s)
	{
		return new XList<>(s.split(""));
	}

	static XList<String> ofTokens(String s)
	{
		return new XList<>(s.split("\\s+"));
	}

	static XList<String> ofTokens(String s, String p)
	{
		return new XList<>(s.split(p));
	}

	XList<T> union(T... t)
	{
		return union(Arrays.asList(t));
	}

	XList<T> union(Collection<T> c)
	{
		XList<T> xlist = new XList(this);
		xlist.addAll(c);
		return xlist;
	}

	XList<T> diff(Collection<T> c)
	{
		XList<T> xlist = new XList(this);
		xlist.removeAll(c);
		return xlist;
	}

	XList<T> unique()
	{
		return new XList<>(stream().distinct().collect(Collectors.toList()));
	}

	<E> XList<XList<E>> combine()
	{
		XList<XList<E>> list = new XList<>();
		list.add(new XList<>());
		for(Iterator<Collection<E>> i = ((XList<Collection<E>>) this).iterator(); i.hasNext();)
		{
			product(list, new XList<>(i.next()));
		}
		return list;
	}

	<E> void product(XList<XList<E>> multiplicand, XList<E> multiplier)
	{
		int size = multiplicand.size();
		for(int i = 1; i < multiplier.size(); ++i)
		{
			for(int j = 0; j < size; ++j)
			{
				multiplicand.add(new XList<>(multiplicand.get(j)));
			}
		}
		for(int i = 0; i < multiplier.size(); ++i)
		{
			for(int j = 0; j < size; ++j)
			{
				multiplicand.get(j + i * size).add(multiplier.get(i));
			}
		}
	}

	<R> XList<R> collect(Function<T, R> f)
	{
		return new XList<R>(this.stream().map(f).collect(Collectors.toList()));
	}

	String join()
	{
		return join("");
	}

	String join(String separator)
	{
		return stream().map(n -> String.valueOf(n)).collect(Collectors.joining(separator));
	}

	void forEachWithIndex(BiConsumer<T, Integer> consumer)
	{
		for(int i = 0; i < size(); ++i) consumer.accept(get(i), i);
	}
}
