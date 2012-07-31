package com.wing.price_it_rite;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MailActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    @Override
    public void onStart() {
        super.onStart();
        
        //set click listener to button
        Button button_1_1 = (Button)findViewById(R.id.percentage_button_1_1);
        Button button_1_2 = (Button)findViewById(R.id.percentage_button_1_2);
        Button button_2_1 = (Button)findViewById(R.id.percentage_button_2_1);
        Button button_2_2 = (Button)findViewById(R.id.percentage_button_2_2);
        
        button_1_1.setOnClickListener(this);
        button_1_2.setOnClickListener(this);
        button_2_1.setOnClickListener(this);
        button_2_2.setOnClickListener(this);
    }

	public void onClick(View v) {
		// TODO Auto-generated method stub
		EditText profit_percentage_edit_text = (EditText)findViewById(R.id.profit_editText);
		String percentage = v.getTag().toString();
		profit_percentage_edit_text.setText(percentage + "%");
		profit_percentage_edit_text.setTag(percentage);
	}
	

    // Called first time user clicks on the menu hard key button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.menu, menu);
    	return true;
    }
    
    //Called when an options item is clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    	case R.id.itemPrefs:
    		startActivity(new Intent(this, SettingsPrefrences.class));
    	}
    	return true;
    }

}