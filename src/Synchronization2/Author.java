package Synchronization2;

import static java.lang.Thread.sleep;

public class Author implements Runnable
{
	String[] args;
	Texts texts;

	Author(String[] args)
	{
		this.args = args;
		texts = new Texts();
	}

	@Override
	public void run()
	{
		for(int i = 0; i < args.length; ++i)
		{
			try
			{
				sleep(1000);
				texts.setTextToWrite(args[i]);
			}
			catch(InterruptedException exc) {}
		}
	}
}
