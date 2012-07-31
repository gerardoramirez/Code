package info.gerardoramirez.android.taskmanager;

import info.gerardoramirez.android.taskmanager.tasks.Task;
import android.content.Intent;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.location.Address;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddTaskActivity extends TaskManagerActivity {

	private static final int REQUEST_CHOOSE_ADDRESS = 0;
	
	private Button addButton;
	private Button cancelButton;
	private EditText taskNameEditText;
	private boolean changesPending;
	private AlertDialog unsavedChangesDialog;
	private AlertDialog emptyTextDialog;
	private Address address;
	private View addLocationButton;
	private View editButton;
	private TextView addressText;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_tasks);	
		setUpViews();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if(null == address) {
			addLocationButton.setVisibility(View.VISIBLE);
			editButton.setVisibility(View.GONE);
			addressText.setVisibility(View.GONE);
		}
		else {
			addLocationButton.setVisibility(View.GONE);
			editButton.setVisibility(View.VISIBLE);
			addressText.setVisibility(View.VISIBLE);
			addressText.setText(address.getAddressLine(0));
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(REQUEST_CHOOSE_ADDRESS == requestCode && RESULT_OK == resultCode) {
			address = data.getParcelableExtra(AddLocationMapActivity.ADDRESS_RESULT);
		}
		else {
			super.onActivityResult(requestCode, resultCode, data);
		}
	}
	
	protected void cancel() {
		if(changesPending) {
			createUnsavedAlertDialog();
		}
		else {
			finish();
		}
	}

	protected void addTask() {
		String taskName = taskNameEditText.getText().toString();
		Task t = new Task(taskName);
		t.setAddress(address);
		
		if (!t.toString().equals("")) {
			getTaskManagerApplication().addTask(t);	
			finish();
		}
		else {
			// print a dialog box that the user didn't enter text
			createEmptyTextAlertDialog();
		}
	}
	
	public void addLocationButtonClicked(View view) {
		Intent intent = new Intent(this, AddLocationMapActivity.class);
		startActivityForResult(intent, REQUEST_CHOOSE_ADDRESS);
	}
	
	public void editButtonClicked(View view) {
		Intent intent = new Intent(this, EditLocationMapActivity.class);
		intent.putExtra("location", address);
		startActivityForResult(intent, REQUEST_CHOOSE_ADDRESS);
	}
 
	private void setUpViews() {
		taskNameEditText = (EditText)findViewById(R.id.task_name);
		
		addressText = (TextView)findViewById(R.id.address_text);
				
		addButton = (Button)findViewById(R.id.add_button);
		cancelButton = (Button)findViewById(R.id.cancel_button);
		editButton = (Button)findViewById(R.id.edit_button);
		addLocationButton =(Button)findViewById(R.id.add_location_button);

		
		setUpAddButton();
		setUpCancelButton();
		setOnTextChanged();
		
	}

	private void setOnTextChanged() {
		taskNameEditText.addTextChangedListener(new TextWatcher() {
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				changesPending = true;
			}
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			public void afterTextChanged(Editable s) { }
		});
	}

	private void setUpCancelButton() {
		cancelButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				cancel();
			}
		});		
	}

	private void setUpAddButton() {
		addButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				addTask();
			}
		});
	}

	public void createUnsavedAlertDialog() {
		unsavedChangesDialog = new AlertDialog.Builder(this)
		.setTitle(R.string.unsaved_changes_title)
		.setMessage(R.string.unsaved_changes_message)
		.setPositiveButton(R.string.add_task, new AlertDialog.OnClickListener() {			
			public void onClick(DialogInterface dialog, int which) {
				addTask();		
			}
		})
		.setNeutralButton(R.string.discard, new AlertDialog.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				finish();
			}
		})
		.setNegativeButton(android.R.string.cancel, new AlertDialog.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				unsavedChangesDialog.cancel();
			}
		})
		.create();
		unsavedChangesDialog.show();
	}
	
	public void createEmptyTextAlertDialog() {

		emptyTextDialog = new AlertDialog.Builder(this)
		.setTitle(R.string.oops_title)
		.setMessage(R.string.empty_text_message)
		.setPositiveButton(R.string.ok, new AlertDialog.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				emptyTextDialog.dismiss();	
			}
		})
		.create();
		emptyTextDialog.show();
	}

}
