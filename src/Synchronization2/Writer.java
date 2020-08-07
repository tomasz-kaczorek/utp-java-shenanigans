package Synchronization2;

public class Writer implements Runnable
{
	Author author;

	Writer(Author author)
	{
		this.author = author;
	}

	@Override
	public void run()
	{
		try
		{
			String text = author.texts.getTextToWrite();
			while(text != null)
			{
				System.out.println("-> " + text);
				text = author.texts.getTextToWrite();
			}
		}
		catch (InterruptedException e) {e.printStackTrace();}
	}
}
