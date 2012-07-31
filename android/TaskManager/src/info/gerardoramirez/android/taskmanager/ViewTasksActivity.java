package info.gerardoramirez.android.taskmanager;

import info.gerardoramirez.android.taskmanager.adapter.TaskListAdapter;
import info.gerardoramirez.android.taskmanager.tasks.Task;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class ViewTasksActivity extends ListActivity {
	
    private Button addButton;
	private TaskManagerApplication app;
	private TaskListAdapter adapter;
	private Button removeButton;
	private AlertDialog confirmRemoveChangesDialog;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setupViews();
        
        app = (TaskManagerApplication)getApplication();
        adapter = new TaskListAdapter (app.getCurrentTasks(), this);
        setListAdapter(adapter);
    }
    
    public void onResume() {
    	super.onResume();  	
    	adapter.forceReload();
    }

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		adapter.toggleTaskCompleteAtPosition(position);
		//
		// check the flag in the database
		//
		Task t = adapter.getItem(position);
		app.saveTask(t);
	}
	
	protected void removeCompletedTasks() {
		Long [] ids = adapter.removeCompletedTasks();
		app.deleteTasks(ids);
	}
	
	private void setupViews() {
		addButton = (Button)findViewById(R.id.add_button);
		removeButton = (Button)findViewById(R.id.remove_button);
		
		addButton.setOnClickListener(new View.OnClickListener() {		
			public void onClick(View v) {
				Intent intent = new Intent(ViewTasksActivity.this, AddTaskActivity.class);
				startActivity(intent);
				
			}
		});
		
		removeButton.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View v) {
				 createConfirmRemoveAlertDialog();
			}
		});
	}
	
	public void createConfirmRemoveAlertDialog() {
		//
		// display a dialog box
		//
		confirmRemoveChangesDialog = new AlertDialog.Builder(this)
		.setTitle(R.string.remove_confirm_title)
		.setMessage(R.string.remove_confirm_message)
		.setPositiveButton(R.string.ok, new AlertDialog.OnClickListener() {			
			public void onClick(DialogInterface dialog, int which) {
				removeCompletedTasks();
			}
		})
		.setNegativeButton(android.R.string.cancel, new AlertDialog.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				confirmRemoveChangesDialog.cancel();
			}
		})
		.create();
		confirmRemoveChangesDialog.show();
	}
	
	
}