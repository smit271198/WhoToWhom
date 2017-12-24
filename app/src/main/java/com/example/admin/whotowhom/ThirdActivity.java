package com.example.admin.whotowhom;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;

public class ThirdActivity extends AppCompatActivity {
    int no;
    String name[];
    double val[];
    double ret[];
    double take[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LinearLayout ll = findViewById(R.id.ll);
        Intent in3=getIntent();
        Bundle b=in3.getBundleExtra("bundle");
        name=b.getStringArray("Names");
        val=b.getDoubleArray("Value");
        no=b.getInt("Number");
        ret=new double[no];
        take=new double[no];
        double totalAmt=0;
        for(int i=0;i<no;i++){
            totalAmt += val[i];
        }
        double avgAmt=totalAmt/no;
        for(int i=0;i<no;i++){
            if(val[i]<avgAmt){
                ret[i]=avgAmt-val[i];
                take[i]=0;
            }
            else if(val[i]>avgAmt){
                ret[i]=0;
                take[i]=val[i]-avgAmt;
            }
            else if(val[i]==avgAmt) {
                ret[i] = 0;
                take[i]=0;
            }
        }



        Display display = ((WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth()/1;
        LinearLayout l = new LinearLayout(this);
        l.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, LinearLayout.LayoutParams.MATCH_PARENT);
        for(int i=0;i<no;i++){
            if(take[i]>0){
                for(int j=0;j<no;j++){
                    if(take[i]!=0){
                        if(ret[j]>0){
                            EditText et1 = new EditText(this);
                            if((take[i]-ret[j])>0){
                                et1.setText(name[i]+" has to take money from "+name[j]+" = "+ret[j]);
                                //System.out.println(name[i]+" has to take money from "+name[j]+" = "+ret[j]);
                                l.addView(et1,lp);
                                take[i]=take[i]-ret[j];
                                ret[j]=0;
                            }
                            else if((take[i]-ret[j])<0){
                                et1.setText(name[i]+" has to take money from "+name[j]+" = "+take[i]);
                                l.addView(et1,lp);
                                //System.out.println(name[i]+" has to take money from "+name[j]+" = "+take[i]);
                                take[i]=0;
                                ret[j]=ret[j]-take[i];
                            }
                            else if((take[i]-ret[j])==0){
                                et1.setText(name[i]+" has to take money from "+name[j]+" = "+ret[j]);
                                l.addView(et1,lp);
                                //System.out.println(name[i]+" has to take money from "+name[j]+" = "+ret[j]);
                                take[i]=0;
                                ret[j]=0;
                            }
                        }
                    }
                    else{
                        break;
                    }
                }
            }
        }
        ll.addView(l);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
