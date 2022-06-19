package com.thesound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class main3 extends AppCompatActivity {
    Button button5;
    Button button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(main3.this, asksee.class);
                startActivity(i);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(main3.this, petitionboard.class);
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
                        case R.id.go_to_search3:
                            Intent intent1=new Intent(getApplicationContext(),surch3.class);
                            startActivity(intent1);
                            return true;
                        case R.id.go_to_all_bulletin_board3:
                            Intent intent=new Intent(getApplicationContext(),petitionboard3.class);
                            startActivity(intent);
                            return true;
                        case R.id.go_to_home3:
                            Intent intent2=new Intent(getApplicationContext(),main3.class);
                            startActivity(intent2);
                            return true;
                        case R.id.go_to_suggestionan_swered:
                            Intent intent3=new Intent(getApplicationContext(),our_school_bullentin_board3.class);
                            startActivity(intent3);
                            return true;
                        case R.id.view_authentication_requests:
                            Intent intent4=new Intent(getApplicationContext(),asksee.class);
                            startActivity(intent4);
                            return true;
                    }
                    return false;
                }
            };
}