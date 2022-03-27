package com.thesound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main extends AppCompatActivity {
    Button button5;
    TextView textView8;
    Button button6;
    TextView textView32;
    TextView textView19;
    TextView textView22;
    TextView textView7;
    TextView textView12;
    TextView textView17;
    TextView textView20;
    TextView textView23;
    TextView textView9;
    TextView textView10;
    TextView textView13;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView32 = (TextView) findViewById(R.id.textView32);
        textView19 = (TextView) findViewById(R.id.textView19);
        textView22 = (TextView) findViewById(R.id.textView22);
        textView8 = (TextView) findViewById(R.id.textView8);
        textView7 = (TextView) findViewById(R.id.textView7);
        textView12 = (TextView) findViewById(R.id.textView12);
        textView17 = (TextView) findViewById(R.id.textView17);
        textView20 = (TextView) findViewById(R.id.textView20);
        textView23 = (TextView) findViewById(R.id.textView23);
        textView9 = (TextView) findViewById(R.id.textView9);
        textView10 = (TextView) findViewById(R.id.textView10);
        textView13 = (TextView) findViewById(R.id.textView13);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Main.this, petiton.class);
                startActivity(i);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Main.this, storagebox.class);
                startActivity(i);
            }
        });
    }
}
