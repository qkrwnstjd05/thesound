package com.thesound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class petiton extends AppCompatActivity {
    EditText ediTextTextMultiLine;
    EditText ediTextTextMultiLine2;
    private ImageView uploadimage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petiton);

        ediTextTextMultiLine = (EditText) findViewById(R.id.editTextTextMultiLine);
        ediTextTextMultiLine2 = (EditText) findViewById(R.id.editTextTextMultiLine2);
        uploadimage= (ImageView)findViewById(R.id.uploadimage);

        String studentUid=FirebaseAuth.getInstance().getCurrentUser().getUid();
        Button b1=(Button) findViewById(R.id.button12);
        FirebaseFirestore db=FirebaseFirestore.getInstance();

        /*db.collection("studentUser").document('')
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "청원이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(petiton.this, Main.class);
                        startActivity(i);
                    }
                });*/



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date c = Calendar.getInstance().getTime();
                List likes=new ArrayList();
                petition_dto petition_dto=new petition_dto(0,likes,ediTextTextMultiLine.getText().toString(),ediTextTextMultiLine2.getText().toString(),studentUid,"어디학교인지","",false,"",c);

                db.collection("petition").document().set(petition_dto.getInfo())
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(), "청원이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(petiton.this, Main.class);
                                startActivity(i);
                            }
                        });
            }
            //엘범에서 사진 끌어오기
        });
        BottomNavigationView bottomNavigation  = findViewById(R.id.bottomNav);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

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