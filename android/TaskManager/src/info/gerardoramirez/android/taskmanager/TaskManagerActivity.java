package info.gerardoramirez.android.taskmanager;

import android.app.Activity;

public class TaskManagerActivity extends Activity {

	protected TaskManagerApplication getTaskManagerApplication() {
		TaskManagerApplication tma = (TaskManagerApplication) getApplication();
		return tma;
	}


}
