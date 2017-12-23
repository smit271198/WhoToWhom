package com.example.admin.whotowhom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ThirdActivity extends AppCompatActivity {
    int no;
    String name[];
    double val[];
    double ret[];
    double take[];
    String show[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Intent in3=getIntent();
        Bundle b=in3.getBundleExtra();
        no=b.getInt("Number");
        name=b.getStringArray("Names");
        val=b.getDoubleArray("Value");
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
    }

    @Override
    protected void onStart() {
        super.onStart();
        for(int i=0,m=0;i<no;i++){
            if(take[i]>0){
                for(int j=0;j<no;j++){
                    if(take[i]!=0){
                        if(ret[j]>0){
                            if((take[i]-ret[j])>0){
                                System.out.println(name[i]+" has to take money from "+name[j]+" = "+ret[j]);
                                take[i]=take[i]-ret[j];
                                ret[j]=0;
                            }
                            else if((take[i]-ret[j])<0){
                                System.out.println(name[i]+" has to take money from "+name[j]+" = "+take[i]);
                                take[i]=0;
                                ret[j]=ret[j]-take[i];
                            }
                            else if((take[i]-ret[j])==0){
                                System.out.println(name[i]+" has to take money from "+name[j]+" = "+ret[j]);
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
