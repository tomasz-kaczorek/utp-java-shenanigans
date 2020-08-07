package Generics;

public interface Mapper<T, R>
{
	R map(T value);
}
