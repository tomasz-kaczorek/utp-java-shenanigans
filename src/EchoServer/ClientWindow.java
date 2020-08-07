package EchoServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientWindow extends JFrame
{
	Socket socket;
	JTextArea inputTextArea = new JTextArea();
	JTextField outputTextField = new JTextField();

	ClientWindow(int port)
	{
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new GridBagLayout());

		inputTextArea.setEditable(false);
		inputTextArea.setLineWrap(true);
		inputTextArea.setWrapStyleWord(true);
		inputTextArea.setText("Write message in the field above and press Enter to send...\n");

		add(outputTextField, new GridBagConstraints(0, 0, 1, 1, 1., 1., GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
		add(inputTextArea, new GridBagConstraints(0, 1, 1, 1, 1., 1000., GridBagConstraints.SOUTH, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				try
				{
					socket.close();
				}
				catch (IOException e) {}
				super.windowClosing(windowEvent);
			}
		});

		try
		{
			socket = new Socket("127.0.0.1", port);
			ExecutorService executorService = Executors.newFixedThreadPool(2);
			InputStreamReader reader = new InputStreamReader(socket.getInputStream());
			OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());

			outputTextField.addActionListener(event -> executorService.submit(() ->
			{
				try
				{
					writer.write(outputTextField.getText() + "\n");
					writer.flush();
					SwingUtilities.invokeLater(() -> outputTextField.setText(""));
				}
				catch (IOException e) {}
			}));

			executorService.submit(() ->
			{
				try
				{
					char[] buffer = new char[4096];
					while(true)
					{
						int count = reader.read(buffer, 0, 4096);
						if(count < 0) throw new IOException();
						SwingUtilities.invokeLater(() -> inputTextArea.append(new String(buffer, 0, count)));
					}
				}
				catch (IOException e) {inputTextArea.append("Connection lost.");}
			});
		}
		catch (IOException e) {}

		setSize(500, 500);
		setVisible(true);
	}
}
