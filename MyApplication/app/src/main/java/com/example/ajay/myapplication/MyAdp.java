package com.example.ajay.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ajay on 10/7/17.
 */

public class MyAdp extends ArrayAdapter<Medicine> {
    Context ctx;
    List<Medicine> medList;
    public MyAdp(Context context, int resource, List<Medicine> objects) {
        super(context, resource, objects);
        ctx = context;
        medList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inf = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
        View row = inf.inflate(R.layout.cust, null);
        TextView tv1 = (TextView) row.findViewById(R.id.textView8);
        TextView tv2 = (TextView) row.findViewById(R.id.textView7);
        TextView tv3 = (TextView) row.findViewById(R.id.textView6);
        TextView tv4 = (TextView) row.findViewById(R.id.textView5);
        TextView tv5 = (TextView) row.findViewById(R.id.textView4);
        TextView tv6 = (TextView) row.findViewById(R.id.textView3);
        Medicine m1 = medList.get(position);
        tv1.setText("Name: " + m1.getName());
        tv2.setText("Start Date: " + m1.getStartDate());
        tv3.setText("End Date: " + m1.getEndDate());
        tv4.setText("Time: " + m1.getTime());
        tv5.setText("Dosage: " + m1.getDosage());
        tv6.setText("Instruction: " + m1.getInstruction());
        ImageView iv1 = (ImageView) row.findViewById(R.id.imageView2);
        switch (m1.getImgType()) {
            case "img1" :
                iv1.setImageResource(R.drawable.img1);
                break;

            case "img2" :
                iv1.setImageResource(R.drawable.img2);
                break;

            case "img3" :
                iv1.setImageResource(R.drawable.img3);
                break;

            case "img4" :
                iv1.setImageResource(R.drawable.img4);
                break;

            case "img5" :
                iv1.setImageResource(R.drawable.img5);
                break;

            case "img6" :
                iv1.setImageResource(R.drawable.img6);
                break;

            case "img7" :
                iv1.setImageResource(R.drawable.img7);
                break;

            case "img8" :
                iv1.setImageResource(R.drawable.img8);
                break;

            case "img9" :
                iv1.setImageResource(R.drawable.img9);
                break;

            case "img10" :
                iv1.setImageResource(R.drawable.img10);
                break;
        }

        return row;
    }
}
