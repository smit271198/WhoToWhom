package com.example.admin.whotowhom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText text1;
    Button btn1;
    String s1;
    int no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text1=(EditText)findViewById(R.id.editText);
        btn1=(Button)findViewById(R.id.button);
    }

    @Override
    protected void onStart() {
        super.onStart();
        btn1.setOnClickListener(v->{
            s1=text1.getText().toString();
            no=Integer.parseInt(s1);
            Intent in1=new Intent(MainActivity.this,SecondActivity.class);
            Bundle b=new Bundle();
            b.putInt("Number",no);
            in1.putExtra("myBundle",b);
            startActivity(in1);
        });
    }
}
