package Generics;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ListCreator<A>
{
	List<A> accumulator;

	public static <T> ListCreator<T> collectFrom(List<T> list)
	{
		ListCreator<T> creator = new ListCreator<>();
		creator.accumulator = new LinkedList<>(list);
		return creator;
	}

	public ListCreator<A> when(Selector<A> selector)
	{
		Iterator<A> iterator = accumulator.iterator();
		while (iterator.hasNext())
		{
			A element = iterator.next();
			if (!selector.select(element))
			{
				iterator.remove();
			}
		}
		return this;
	}

	public <R> List<R> mapEvery(Mapper<A, R> mapper)
	{
		List<R> mapping = new LinkedList<>();
		for (A element : accumulator)
		{
			mapping.add(mapper.map(element));
		}
		return mapping;
	}
}  
