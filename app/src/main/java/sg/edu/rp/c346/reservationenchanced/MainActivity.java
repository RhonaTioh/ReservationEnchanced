package sg.edu.rp.c346.reservationenchanced;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etTelephone;
    EditText etSize;
    EditText etTime;
    EditText etDay;
    CheckBox cbSmoke;
    Button btnReserve;
    Button btnReset;
    String checked ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etTelephone = findViewById(R.id.editTextPhone);
        etSize = findViewById(R.id.editTextSize);
        etTime = findViewById(R.id.editTextTime);
        etDay = findViewById(R.id.editTextDate);
        cbSmoke = findViewById(R.id.checkBoxSmoke);
        btnReserve = findViewById(R.id.buttonReserve);
        btnReset = findViewById(R.id.buttonReset);

        cbSmoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cbSmoke.isChecked()){
                    checked = "Smoking: Yes";
                }
                else{
                    checked = "Smoking: No";
                }
            }
        });

        etDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        etDay.setText(dayOfMonth + "/" + (monthOfYear+1) + "/" + year);
                    }
                };
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int monthOfYear = c.get(Calendar.MONTH);
                int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this,myDateListener,year,monthOfYear,dayOfMonth);
                myDateDialog.show();
            }
        });
        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        etTime.setText(hourOfDay + ":" + minute);
                    }
                };
                Calendar currentTime = Calendar.getInstance();
                int currenthour = currentTime.get(Calendar.HOUR_OF_DAY);
                int currentminute = currentTime.get(Calendar.MINUTE);
                TimePickerDialog myTimeDialog = new TimePickerDialog(MainActivity.this,myTimeListener,currenthour,currentminute,true);
                myTimeDialog.show();
            }
        });
        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View viewDialog = inflater.inflate(R.layout.input,null);

                final TextView tvName = viewDialog.findViewById(R.id.textViewName);
                final TextView tvSize = viewDialog.findViewById(R.id.textViewSize);
                final TextView tvSmoke = viewDialog.findViewById(R.id.textViewSmoking);
                final TextView tvDate = viewDialog.findViewById(R.id.textViewDate);
                final TextView tvTime = viewDialog.findViewById(R.id.textViewTime);

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);
                myBuilder.setView(viewDialog);
                myBuilder.setTitle("Confirm Your Order");
                tvName.setText("Name: " + etName.getText().toString());
                tvSmoke.setText(checked);
                tvSize.setText("Size: " + etSize.getText().toString());
                tvDate.setText("Date: " + etDay.getText().toString());
                tvTime.setText("Time: " + etTime.getText().toString());
                myBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                myBuilder.setNeutralButton("Cancel",null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etName.setText("");
                etTelephone.setText("");
                etSize.setText("");
                cbSmoke.setChecked(false);
                etDay.setText("");
                etTime.setText("");
            }
        });
    }
}
