 package info.gerardoramirez.android.taskmanager.adapter;

import info.gerardoramirez.android.taskmanager.R;
import info.gerardoramirez.android.taskmanager.tasks.Task;
import info.gerardoramirez.android.taskmanager.views.TaskListItem;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class TaskListAdapter extends BaseAdapter {

	private ArrayList<Task> tasks;
	private Context context;
	
	public TaskListAdapter(ArrayList<Task> tasks, Context context) {
		super();
		this.tasks = tasks;
		this.context = context;
	}

	public int getCount() {		
		return tasks.size();
	}

	public Task getItem(int position) {
		return (null == tasks) ? null : tasks.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		TaskListItem taskListItem;
		
		if(null == convertView) {
			taskListItem = (TaskListItem)View.inflate(context, R.layout.task_list_item, null);
		} else {
			taskListItem = (TaskListItem)convertView;
		}
		taskListItem.setTask(tasks.get(position));
		
		return taskListItem;
	}

	public void forceReload() {
		notifyDataSetChanged();
	}

	public void toggleTaskCompleteAtPosition(int position) {
		Task task = tasks.get(position);
		task.toggleComplete();
		notifyDataSetChanged();
	}

	public Long[] removeCompletedTasks() {
		ArrayList<Task> completedTasks = new ArrayList<Task>();
		ArrayList<Long> completedIds = new ArrayList<Long>();
		
		for(Task task : tasks) {
			if(task.isComplete()) {
				completedIds.add(task.getId());
				completedTasks.add(task);
			}
		}
		tasks.removeAll(completedTasks);
		notifyDataSetChanged();
		

		return completedIds.toArray(new Long[] {});
	}

}
