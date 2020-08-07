package Executors;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TaskList extends JScrollPane
{
	TaskMenu taskMenu = new TaskMenu();
	JList<String> taskList;

	TaskList(ListModel model)
	{
		taskList = new JList<>(model);
		setViewportView(taskList);
		taskList.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isRightMouseButton(e))
				{
					int id = taskList.locationToIndex(e.getPoint());
					taskList.setSelectedIndex(id);
					taskMenu.show(taskList, e.getPoint().x, e.getPoint().y, id >= 0);
				}
			}
		});
	}

	public int getSelectedIndex()
	{
		return taskList.getSelectedIndex();
	}

	public void addMenuListener(ActionListener listener)
	{
		taskMenu.addMenuListener(listener);
	}

	public void removeMenuListener(ActionListener listener)
	{
		taskMenu.removeMenuListener(listener);
	}
}
