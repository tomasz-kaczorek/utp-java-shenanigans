package Executors;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Task implements Callable<String>
{
	String name;
	int delay;
	String returned;

	Task(String name, int delay, String returned)
	{
		this.name = name;
		this.delay = delay;
		this.returned = returned;
	}

	public String getName()
	{
		return name;
	}

	public int getDelay()
	{
		return delay;
	}

	public String getReturned()
	{
		return returned;
	}

	@Override
	public String call()
	{
		try
		{
			TimeUnit.SECONDS.sleep(delay);
			return returned;
		}
		catch (InterruptedException e)
		{
			return null;
		}
	}
}
