package com.thesound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class  login extends AppCompatActivity {
    Button button6;
    Button button5;
    Button button7;
    // 파이어베이스 인증 객체 생성
    private FirebaseAuth firebaseAuth;

    // 이메일과 비밀번호
    ;
    private EditText loginId;
    private EditText loginPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth=FirebaseAuth.getInstance();
        button6 = (Button) findViewById(R.id.button6);
        button5 = (Button) findViewById(R.id.button5);
        button7 = (Button) findViewById(R.id.button7);

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginId=(EditText) findViewById(R.id.loginId);
                loginPw=(EditText) findViewById(R.id.loginPw);
                loginUser(loginId.getText().toString(),loginPw.getText().toString());
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(login.this,signup1.class);
                startActivity(i);
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(login.this,signup2.class);
                startActivity(i);
            }
        });
        //login
    }
    // 로그인
    private void loginUser(String email, String password)
    {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 로그인 성공
                            //정보찾기
                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                            CollectionReference studentRef = db.collection("studentUser");
                            studentRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if(task.isSuccessful()){
                                        for (QueryDocumentSnapshot document:task.getResult()){
                                            if(document.getId().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                                                Intent i = new Intent(login.this,Main.class);
                                                startActivity(i);
                                                finish();
                                            }
                                        }
                                    }
                                }
                            });
                            CollectionReference teacherRef = db.collection("schoolUser");
                            teacherRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if(task.isSuccessful()){
                                        for (QueryDocumentSnapshot document:task.getResult()){
                                            if(document.getId().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                                                Intent i = new Intent(login.this,main3.class);
                                                startActivity(i);
                                                finish();
                                            }
                                        }
                                    }
                                }
                            });
                        } else {
                            // 로그인 실패
                            Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}