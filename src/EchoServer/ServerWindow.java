package EchoServer;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ServerWindow extends JFrame
{
	JComboBox<String> clientComboBox = new JComboBox<>();
	JButton newClientButton = new JButton("New client");
	JButton killClientButton = new JButton("Kill client");
	LinkedList<Socket> sockets = new LinkedList<>();

	ServerWindow(int port)
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());

		add(clientComboBox, new GridBagConstraints(0, 0, 2, 1, 1., 1., GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
		add(newClientButton, new GridBagConstraints(0, 1, 1, 1, 1., 1000., GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
		add(killClientButton, new GridBagConstraints(1, 1, 1, 1, 1., 1000., GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));

		newClientButton.addActionListener(event -> new ClientWindow(10000));
		killClientButton.addActionListener(event ->
		{
			int index = clientComboBox.getSelectedIndex();
			if(index >= 0)
			{
				try
				{
					sockets.remove(index).close();
				}
				catch (IOException e) {e.printStackTrace();}
				SwingUtilities.invokeLater(() -> clientComboBox.removeItemAt(index));
			}
		});

		try
		{
			ExecutorService executorService = Executors.newFixedThreadPool(10);
			ServerSocket serverSocket = new ServerSocket(10000);

			executorService.submit(() ->
			{
				while(true)
				{
					Socket socket = serverSocket.accept();
					sockets.add(socket);
					executorService.submit(new FutureTask<Void>(new EchoTask(socket), null){
						@Override
						protected void done()
						{
							int index = sockets.indexOf(socket);
							if(index >= 0)
							{
								try
								{
									sockets.remove(index).close();
								}
								catch (IOException e) {}
								SwingUtilities.invokeLater(() -> clientComboBox.removeItemAt(index));
							}
						}
					});
					executorService.submit(new FutureTask<>(new EchoTask(socket), null));
					SwingUtilities.invokeLater(() -> clientComboBox.addItem(socket.getInetAddress() + ":" + socket.getPort()));
				}
			});
		}
		catch (IOException e) {dispose();}

		pack();
		setVisible(true);
	}
}
