package Executors;

import java.awt.event.ActionEvent;

public class TaskMenuEvent extends ActionEvent
{
	public static final int ACTION_NEW = ACTION_FIRST;
	public static final int ACTION_CANCEL = ACTION_FIRST + 1;
	public static final int ACTION_STATUS = ACTION_FIRST + 2;
	public static final int ACTION_RESULT = ACTION_FIRST + 3;

	public TaskMenuEvent(Object source, int id, String command)
	{
		super(source, ACTION_FIRST + id, command);
	}
}
