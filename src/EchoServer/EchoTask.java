package EchoServer;

import java.io.*;
import java.net.Socket;

public class EchoTask implements Runnable
{
	Socket socket;

	EchoTask(Socket socket)
	{
		this.socket = socket;
	}

	@Override
	public void run()
	{
		try
		{
			InputStreamReader reader = new InputStreamReader(socket.getInputStream());
			OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());

			while(true)
			{
				char[] buffer = new char[4096];
				writer.write(buffer, 0, reader.read(buffer, 0, 4096));
				writer.flush();
			}
		}
		catch (ArrayIndexOutOfBoundsException | IOException e) {}
	}
}
