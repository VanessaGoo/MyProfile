package sg.edu.rp.c346.myprofile;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etGPA;
    RadioGroup rgGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //This is a new line

        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.radioGroupGender);
    }
    @Override
    protected void onPause() {
        super.onPause();

        //Get the user input from the EditText and store it
        String strName = etName.getText().toString();
        float gpa = Float.parseFloat(etGPA.getText().toString());
        int gender = rgGender.getCheckedRadioButtonId();
        //Obtain an instance of the SharedPrefences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Obtain an instance for update
        SharedPreferences.Editor prefEdit = prefs.edit();
        //Add the key-value pair
        prefEdit.putString("myName",strName);
        prefEdit.putFloat("myGPA",gpa);
        prefEdit.putInt("myGender",gender);
        //Call commit() to save changes
        prefEdit.commit();
    }
    @Override
    protected void onResume() {
        super.onResume();

        //Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Retreive the saved data
        String msg = prefs.getString("myName","No name!");
        float theGPA = prefs.getFloat("myGPA",0.0f);
        int theGender = prefs.getInt("myGender",0);
        //Update the UI element wih the value
        etName.setText(msg);
        etGPA.setText(String.valueOf(theGPA));
        rgGender.check(theGender);
    }
}