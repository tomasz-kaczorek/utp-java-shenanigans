package Executors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Vector;

public class TaskMenu extends JPopupMenu
{
	final Vector<String> names = new Vector<>(Arrays.asList("New...", "Cancel", "Status", "Result"));
	Vector<JMenuItem> items = new Vector<>(names.size());
	LinkedList<ActionListener> listeners = new LinkedList<>();

	TaskMenu()
	{
		for(String name : names)
		{
			JMenuItem item = new JMenuItem(name);
			items.add(item);
			add(item);
			item.addActionListener(e ->
			{
				TaskMenuEvent event = new TaskMenuEvent(e.getSource(), items.indexOf(e.getSource()), e.getActionCommand());
				for(ActionListener listener : listeners) listener.actionPerformed(event);
			});
		}
	}

	public void show(Component invoker, int x, int y, boolean selected)
	{
		for(int i = 1; i < names.size(); ++i) items.get(i).setVisible(selected);
		super.show(invoker, x, y);
	}

	public void addMenuListener(ActionListener listener)
	{
		listeners.add(listener);
	}

	public void removeMenuListener(ActionListener listener)
	{
		listeners.remove(listener);
	}
}
