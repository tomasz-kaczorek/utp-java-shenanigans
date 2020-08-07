package Executors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.*;

public class MainWindow extends JFrame implements ActionListener
{
	DefaultListModel<String> taskListModel = new DefaultListModel<>();
	TaskList taskList = new TaskList(taskListModel);
	Console console = new Console();
	TaskDialog taskDialog = new TaskDialog();
	ExecutorService executor = Executors.newFixedThreadPool(2);
	ArrayList<Future<String>> futures = new ArrayList<>();

	MainWindow()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());

		add(taskList, new GridBagConstraints(0, 0, 1, 1, 1., 1., GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
		taskList.addMenuListener(this);

		add(console, new GridBagConstraints(1, 0, 1, 1, 1., 1., GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));

		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		int index = taskList.getSelectedIndex();
		Future<String> future = index < 0 ? null : futures.get(index);
		switch (event.getID())
		{
			case TaskMenuEvent.ACTION_NEW:
				Task task = taskDialog.showTaskDialog();
				if(task == null) return;
				taskListModel.addElement(task.getName());
				futures.add(executor.submit(task));
				return;
			case TaskMenuEvent.ACTION_CANCEL:
				future.cancel(true);
				return;
			case TaskMenuEvent.ACTION_STATUS:
				console.log("\nTask \"" + taskListModel.elementAt(index) + "\" is ");
				if(future.isCancelled()) console.log("cancelled");
				else if(future.isDone()) console.log("done");
				else console.log("running");
				return;
			case TaskMenuEvent.ACTION_RESULT:
				console.log("\nResult of task \"" + taskListModel.elementAt(index) + "\" is ");
				try
				{
					console.log(future.get(0, TimeUnit.SECONDS));
				}
				catch (CancellationException | InterruptedException | ExecutionException | TimeoutException e)
				{
					console.log("unavailable");
				}
			default:
		}
	}
}
