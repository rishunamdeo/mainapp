package com.example.ajay.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddMedi extends AppCompatActivity {

    EditText sd, ed, noti, t1, t2, t3, t4;
    Button settime;
    Spinner sp, tsp;
    String mediName;
    Calendar myCalendar1, myCalendar2;
    String s1, s2;
    MediaPlayer mp;

    String[] ar = {"Take before meal",
            "Take after meal",
            "Take on an empty stomach",
            "Take with water",
            "Never take with milk",
            "Avoid Sugars", "Avoid salty food",
            "avoid fatty food",
            "Eat more vegetables",
            "Eat more iron-rich foods"};

    String[] ar1 = {"1", "2", "3", "4", "5"};


    EditText et;
    Button b1, prev, next;
    TextView txt;
    int i = 0;
    String medicine;
    String imgType = "img1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medi);

        setdb();

        sd = (EditText) findViewById(R.id.sd);
        t1 = (EditText) findViewById(R.id.t1);
        t2 = (EditText) findViewById(R.id.t2);
        t3 = (EditText) findViewById(R.id.t3);
        t4 = (EditText) findViewById(R.id.t4);
        ed = (EditText) findViewById(R.id.ed);
        noti = (EditText) findViewById(R.id.noti);
        b1 = (Button) findViewById(R.id.btn);
        sp = (Spinner) findViewById(R.id.sp1);
        tsp = (Spinner) findViewById(R.id.tsp);

        et = (EditText) findViewById(R.id.editText);
        prev = (Button) findViewById(R.id.prev);
        next = (Button) findViewById(R.id.next);
        txt = (TextView) findViewById(R.id.txt);
        b1 = (Button) findViewById(R.id.btnConfirm);
        final ImageView iv = (ImageView) findViewById(R.id.imageView);


        ArrayAdapter<String> obj2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ar);
        obj2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(obj2);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> ob3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, ar1);
        ob3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tsp.setAdapter(ob3);
        tsp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = tsp.getSelectedItem().toString();
                int item = Integer.parseInt(str);
                if (item == 1) {
                    noti.setVisibility(View.VISIBLE);
                } else if (item == 2) {
                    t1.setVisibility(View.VISIBLE);
                } else if (item == 3) {
                    t1.setVisibility(View.VISIBLE);
                    t2.setVisibility(View.VISIBLE);
                } else if (item == 4) {
                    t1.setVisibility(View.VISIBLE);
                    t2.setVisibility(View.VISIBLE);
                    t3.setVisibility(View.VISIBLE);

                } else if (item == 5) {
                    t1.setVisibility(View.VISIBLE);
                    t2.setVisibility(View.VISIBLE);
                    t3.setVisibility(View.VISIBLE);
                    t4.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SetTime st = new SetTime(noti, AddMedi.this);
        SetTime st1 = new SetTime(t1, AddMedi.this);
        SetTime st2 = new SetTime(t2, AddMedi.this);
        SetTime st3 = new SetTime(t3, AddMedi.this);
        SetTime st4 = new SetTime(t4, AddMedi.this);

        myCalendar1 = Calendar.getInstance();
        myCalendar2 = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar1.set(Calendar.YEAR, year);
                myCalendar1.set(Calendar.MONTH, monthOfYear);
                myCalendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "dd/MM/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                sd.setText(sdf.format(myCalendar1.getTime()));
            }
        };

        sd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddMedi.this, date, myCalendar1.get(Calendar.YEAR), myCalendar1.get(Calendar.MONTH),
                        myCalendar1.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar1.set(Calendar.YEAR, year);
                myCalendar1.set(Calendar.MONTH, monthOfYear);
                myCalendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "dd/MM/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                ed.setText(sdf.format(myCalendar1.getTime()));
            }
        };

        ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddMedi.this, date1, myCalendar2.get(Calendar.YEAR), myCalendar2.get(Calendar.MONTH), myCalendar2.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicine = et.getText().toString();
                //Toast.makeText(AddMedi.this, medicine, Toast.LENGTH_SHORT).show();
                if (!"".equals(medicine)) {
                    String msg = medicine + " " + imgType + " " + tsp.getSelectedItem().toString() + " " + sp.getSelectedItem().toString() + " " +
                                    sd.getText() + " " + ed.getText() + " " + noti.getText() + " " + t1.getText() + " " + t2.getText() + " " + t3.getText() + " " + t4.getText();
                    Toast.makeText(AddMedi.this, msg, Toast.LENGTH_SHORT).show();
                    insertDB();
                    Intent i1 = new Intent(AddMedi.this, Mylist.class);
                    startActivity(i1);
                    AddMedi.this.finish();
                } else {
                    Toast.makeText(AddMedi.this, "Enter Medicine Name", Toast.LENGTH_SHORT).show();
                }
            }
        });


        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                --i;
                if (i < 1) {
                    i = 10;
                }
                String name = "img" + i;
                imgType = name;
                int rid = getResources().getIdentifier(name, "drawable", getPackageName());
                iv.setImageResource(rid);
                settxt(name);
                Toast.makeText(AddMedi.this, imgType, Toast.LENGTH_SHORT).show();
            }

            private void settxt(String name) {
                switch (name) {
                    case "img1":
                        txt.setText("Capsule");
                        break;
                    case "img2":
                        txt.setText("Drop");
                        break;
                    case "img3":
                        txt.setText("First Aid");
                        break;
                    case "img4":
                        txt.setText("Lotion");
                        break;
                    case "img5":
                        txt.setText("Injection");
                        break;
                    case "img6":
                        txt.setText("Mouth Freshner");
                        break;
                    case "img7":
                        txt.setText("Tablet");
                        break;
                    case "img8":
                        txt.setText("Spray");
                        break;
                    case "img9":
                        txt.setText("Tube");
                        break;
                    case "img10":
                        txt.setText("Inhaler");
                        break;
                }
            }

        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++i;
                if (i > 10) {
                    i = 1;
                }
                String name = "img" + i;
                imgType = name;
                int rid = getResources().getIdentifier(name, "drawable", getPackageName());
                iv.setImageResource(rid);
                settxt(name);
                Toast.makeText(AddMedi.this, imgType, Toast.LENGTH_SHORT).show();
            }


            private void settxt(String name) {
                switch (name) {
                    case "img1":
                        txt.setText("Capsule");
                        break;
                    case "img2":
                        txt.setText("Drop");
                        break;
                    case "img3":
                        txt.setText("First Aid");
                        break;
                    case "img4":
                        txt.setText("Lotion");
                        break;
                    case "img5":
                        txt.setText("Injection");
                        break;
                    case "img6":
                        txt.setText("Mouth Freshner");
                        break;
                    case "img7":
                        txt.setText("Tablet");
                        break;
                    case "img8":
                        txt.setText("Spray");
                        break;
                    case "img9":
                        txt.setText("Tube");
                        break;
                    case "img10":
                        txt.setText("Inhaler");
                        break;
                }
            }

        });

    }

    private void setdb() {
        SQLiteDatabase db = openOrCreateDatabase("data", MODE_PRIVATE, null);
        String sql = "create table if not exists medicine(id INTEGER PRIMARY KEY AUTOINCREMENT, mname varchar(50), s_date DATE , e_date DATE, time varchar(50), img_type varchar(50), dosage varchar(10), instruction varchar(50))";
        db.execSQL(sql);
        db.close();
        Toast.makeText(this, "Db created", Toast.LENGTH_SHORT).show();
    }

    private void insertDB() {
        SQLiteDatabase db = openOrCreateDatabase("data", MODE_PRIVATE, null);
        String insert = "insert into medicine(mname , s_date , e_date , time, img_type, dosage , instruction) values (? , ? , ? , ? , ?, ?, ?) ";
        Object oa[] = new Object[7];
        oa[0] = et.getText().toString();
        oa[1] = sd.getText().toString();
        oa[2] = ed.getText().toString();
        oa[3] = noti.getText().toString() + " " + t1.getText().toString() + " "
                + t2.getText().toString() + " "  + t3.getText().toString() + " " + t4.getText().toString() ;
        oa[4] = imgType;
        oa[5] = tsp.getSelectedItem().toString();
        oa[6] = sp.getSelectedItem().toString();
        db.execSQL(insert, oa);
        db.close();
    }

    class SetTime implements View.OnFocusChangeListener, TimePickerDialog.OnTimeSetListener {

        private EditText editText;
        private Calendar myCalendar;

        public SetTime(EditText editText, Context ctx) {
            this.editText = editText;
            this.editText.setOnFocusChangeListener(this);
            this.myCalendar = Calendar.getInstance();
        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
                int minute = myCalendar.get(Calendar.MINUTE);
                new TimePickerDialog(AddMedi.this, this, hour, minute, true).show();
            }
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            this.editText.setText(hourOfDay + ":" + minute);
        }
    }
}
