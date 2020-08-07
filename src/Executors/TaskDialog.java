package Executors;

import javax.swing.*;

public class TaskDialog extends JOptionPane
{
	JTextField nameLabel = new JTextField();
	JSpinner executionTimeSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 60, 1));
	JTextField returnedLabel = new JTextField();
	final JComponent[] inputs = new JComponent[] {
		new JLabel("Task name"),
		nameLabel,
		new JLabel("Execution time (secs)"),
		executionTimeSpinner,
		new JLabel("Return value (String)"),
		returnedLabel
	};

	Task showTaskDialog()
	{
		int result = JOptionPane.showConfirmDialog(null, inputs, "New task...", JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION && !nameLabel.getText().isEmpty())
		{
			return new Task(nameLabel.getText(), (Integer) executionTimeSpinner.getValue(), returnedLabel.getText());
		}
		else
		{
			return null;
		}
	}
}
