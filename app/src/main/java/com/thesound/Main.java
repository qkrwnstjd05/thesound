package com.thesound;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main extends AppCompatActivity {
    Button button5;
    TextView textView8;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView8=(TextView)findViewById(R.id.textView8);
        button5=(Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                if(i%2==0){
                    textView8.setText("김윤기는 짝수만큼 바보다");
                }
                else{
                    textView8.setText("김윤기는 홀수 보다 바보다");
                }

            }
        });
    }
}