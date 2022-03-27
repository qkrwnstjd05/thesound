package com.thesound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class petiton extends AppCompatActivity {
    EditText ediTextTextMultiLine;
    EditText ediTextTextMultiLine2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petiton);

        ediTextTextMultiLine = (EditText) findViewById(R.id.editTextTextMultiLine);
        ediTextTextMultiLine2 = (EditText) findViewById(R.id.editTextTextMultiLine2);

        String studentUid=FirebaseAuth.getInstance().getCurrentUser().getUid();
        Button b1=(Button) findViewById(R.id.button12);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date c = Calendar.getInstance().getTime();
                List likes=new ArrayList();
                petition_dto petition_dto=new petition_dto(0,likes,"","",studentUid,"어디학교인지","",false,"",c);
                FirebaseFirestore db=FirebaseFirestore.getInstance();
                db.collection("petition").document().set(petition_dto)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(), "청원이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(petiton.this, Main.class);
                                startActivity(i);
                            }
                        });
            }
        });
    }
}