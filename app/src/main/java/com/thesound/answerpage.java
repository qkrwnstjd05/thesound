package com.thesound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class answerpage extends AppCompatActivity {
    Button button16;
    EditText editTextTextpetitonanswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answerpage);

        editTextTextpetitonanswer = (EditText) findViewById(R.id.editTextTextpetitonanswer);

        String studentUid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        Button b1=(Button) findViewById(R.id.button16);
        FirebaseFirestore db=FirebaseFirestore.getInstance();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //db.collection("petition").document("Er7Qqwkr1PqaWgUsytl6").set("answer").//
            }
        });

        BottomNavigationView bottomNavigation  = findViewById(R.id.bottomNav);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        MenuItem item = bottomNavigation.getMenu().findItem(R.id.go_to_all_bulletin_board3);
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
