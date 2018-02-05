package com.example.timeticker;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView myTextView;
    public android.os.Handler Handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTextView = (TextView) findViewById(R.id.textView3);
        myTextView.setText("initial");


        new Thread( new TimeTicker()).start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) { }
        Handler = new Handler() {
            @Override public void handleMessage(Message msg) {
                String text = (String)msg.obj;
            }
        };

    }

    class TimeTicker implements Runnable{


        @Override
        public void run() {

            while(true){

                Log.d("GVRActivity", " System.currentTimeMillis() = "+ System.currentTimeMillis());

                try {
                    Thread.sleep(4);
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MainActivity.this.myTextView.setText(Long.toString(System.currentTimeMillis()));
                    }
                });

            }


        }
    }
}
