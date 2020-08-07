package Lambdas1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListCreator<A>
{
	List<A> accumulator;

	public static <T> ListCreator<T> collectFrom(List<T> list)
	{
		ListCreator<T> creator = new ListCreator<>();
		creator.accumulator = new LinkedList<>(list);
		return creator;
	}

	public ListCreator<A> when(Predicate<A> predicate)
	{
		Iterator<A> iterator = accumulator.iterator();
		while (iterator.hasNext())
		{
			A element = iterator.next();
			if (!predicate.test(element))
			{
				iterator.remove();
			}
		}
		return this;
	}

	public <R> List<R> mapEvery(Function<A, R> function)
	{
		List<R> mapping = new LinkedList<R>();
		for (A element : accumulator)
		{
			mapping.add(function.apply(element));
		}
		return mapping;
	}
}  
