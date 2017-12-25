package com.example.admin.whotowhom;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    int no;
    List<EditText> names = new ArrayList<EditText>();
    List<EditText> values = new ArrayList<EditText>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent in2=getIntent();
        Bundle b2=in2.getBundleExtra("myBundle");
        no=Integer.parseInt(b2.getString("Number"));
        LinearLayout ll = findViewById(R.id.ll);
        Display display = ((WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth()/2;
        //LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, LinearLayout.LayoutParams.WRAP_CONTENT);
        for(int i=0;i<no;i++){
            LinearLayout l = new LinearLayout(this);
            l.setOrientation(LinearLayout.HORIZONTAL);
            EditText et1 = new EditText(this);
            et1.setId(i);
            names.add(et1);
            EditText et2 = new EditText(this);
            et2.setId(i);
            values.add(et2);
            et1.setHint("Name "+(i+1));
            et2.setHint("Rupees"+(i+1));
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, LinearLayout.LayoutParams.WRAP_CONTENT);
            l.addView(et1,lp);
            l.addView(et2,lp);
            ll.addView(l);
        }
        LinearLayout l = new LinearLayout(this);
        l.setOrientation(LinearLayout.HORIZONTAL);
        Button b = new Button(this);
        b.setText("proceed");
        String name[]=new String[no];
        double value[]=new double[no];
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<no;i++){
                    name[i]=names.get(i).getText().toString();
                    value[i]=Double.parseDouble(values.get(i).getText().toString());
                }
                Intent in2=new Intent(SecondActivity.this,ThirdActivity.class);
                Bundle b=new Bundle();
                b.putStringArray("Names",name);
                b.putDoubleArray("Value",value);
                b.putInt("Number",no);
                in2.putExtra("bundle",b);
                startActivity(in2);
            }
        });

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width,LinearLayout.LayoutParams.WRAP_CONTENT);
        //ScrollView sv=new ScrollView(this);
        //sv.setLayoutParams(lp);
        l.addView(b,lp);
        ll.addView(l);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}