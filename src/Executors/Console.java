package Executors;

import javax.swing.*;

public class Console extends JScrollPane
{
	JTextArea textArea = new JTextArea("RMB on task list to the left for more options...");

	Console()
	{
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		setViewportView(textArea);
	}

	void log(String text)
	{
		textArea.append(text);
	}
}
