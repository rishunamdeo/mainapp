package com.example.ajay.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.LinkedList;

public class Mylist extends AppCompatActivity {

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylist);
        lv = (ListView) findViewById(R.id.listView);
        SQLiteDatabase db = openOrCreateDatabase("data", MODE_PRIVATE, null);
        String sql = "select * from medicine";
        Cursor c = db.rawQuery(sql, null);
        int in1 = c.getColumnIndex("mname");
        int in2 = c.getColumnIndex("s_date");
        int in3 = c.getColumnIndex("e_date");
        int in4 = c.getColumnIndex("time");
        int in5 = c.getColumnIndex("img_type");
        int in6 = c.getColumnIndex("dosage");
        int in7 = c.getColumnIndex("instruction");


/*        LinkedList<String> res = new LinkedList<>();
        int i = 1;
        while (c.moveToNext()) {
            String name = c.getString(in1);
            String sdate = c.getString(in2);
            String edate = c.getString(in3);
            String time = c.getString(in4);
            String imgType = c.getString(in5);
            String dosage = c.getString(in6);
            String inst = c.getString(in7);
            res.add(i + " - " + name + " \n" + sdate + " \n" + edate + "\n" + time + "\n" + imgType + "\n" + dosage + "\n" + inst);
            i++;
        }
        ArrayAdapter<String> aa = new ArrayAdapter<String>(Mylist.this, android.R.layout.simple_list_item_1, res);
        lv.setAdapter(aa);

*/

        LinkedList<Medicine> res = new LinkedList<>();
        while (c.moveToNext()) {
            Medicine m1 = new Medicine();
            m1.setName(c.getString(in1));
            m1.setStartDate(c.getString(in2));
            m1.setEndDate(c.getString(in3));
            m1.setTime(c.getString(in4));
            m1.setImgType(c.getString(in5));
            m1.setDosage(c.getString(in6));
            m1.setInstruction(c.getString(in7));
            res.add(m1);
        }
        MyAdp aa = new MyAdp(Mylist.this, R.layout.cust, res);
        lv.setAdapter(aa);

        db.close();
        Toast.makeText(Mylist.this, "Mylist", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Intent i1 = new Intent(Mylist.this, MainActivity.class);
        startActivity(i1);
        Mylist.this.finish();
    }
}
