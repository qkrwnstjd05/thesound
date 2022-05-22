package com.thesound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Main extends AppCompatActivity {
    Button button5;
    Button button6;
    TextView weschool_1;
    TextView weschool_2;
    TextView weschool_3;
    TextView wschool_1;
    TextView wschool_2;
    TextView wschool_3;
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

        /*weschool_1 = (TextView) findViewById(R.id.weschool_1);
        weschool_2 = (TextView) findViewById(R.id.weschool_2);
        weschool_3 = (TextView) findViewById(R.id.weschool_3);
        wschool_1 = (TextView) findViewById(R.id.wschool_1);
        wschool_2 = (TextView) findViewById(R.id.wschool_2);
        wschool_3 = (TextView) findViewById(R.id.wschool_3);
        textView17 = (TextView) findViewById(R.id.textView17);
        textView20 = (TextView) findViewById(R.id.textView20);
        textView23 = (TextView) findViewById(R.id.textView23);
        textView9 = (TextView) findViewById(R.id.textView9);
        textView10 = (TextView) findViewById(R.id.textView10);
        textView13 = (TextView) findViewById(R.id.textView13);*/
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
                Intent i = new Intent(Main.this, petitionboard.class);
                startActivity(i);
            }
        });

        BottomNavigationView bottomNavigation  = findViewById(R.id.bottomNav);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        MenuItem item = bottomNavigation.getMenu().findItem(R.id.go_to_home);
        item.setChecked(true);

    }
    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.go_to_search:
                            Intent intent1=new Intent(getApplicationContext(),surch.class);
                            startActivity(intent1);
                            return true;
                        case R.id.go_to_all_bulletin_board:
                            Intent intent=new Intent(getApplicationContext(),petitionboard.class);
                            startActivity(intent);
                            return true;
                        case R.id.go_to_home:
                            Intent intent2=new Intent(getApplicationContext(),Main.class);
                            startActivity(intent2);
                            return true;
                        case R.id.go_to_our_school_bulletin_board:
                            Intent intent3=new Intent(getApplicationContext(),our_school_bullentin_board.class);
                            startActivity(intent3);
                            return true;
                        case R.id.go_to_inbox:
                            Intent intent4=new Intent(getApplicationContext(),storagebox.class);
                            startActivity(intent4);
                            return true;
                    }
                    return false;
                }
            };
}
