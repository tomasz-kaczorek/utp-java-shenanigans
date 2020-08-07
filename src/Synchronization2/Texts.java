package Synchronization2;

import java.util.concurrent.LinkedBlockingQueue;

public class Texts
{
	LinkedBlockingQueue<String> texts = new LinkedBlockingQueue<>();

	void setTextToWrite(String text) throws InterruptedException
	{
		texts.put(text);
	}

	String getTextToWrite() throws InterruptedException
	{
		return texts.take();
	}
}
