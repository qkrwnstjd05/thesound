package com.thesound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup1 extends AppCompatActivity {

    // 파이어베이스 인증 객체 생성
    private FirebaseAuth firebaseAuth;

    // 이메일과 비밀번호
    private EditText textId;
    private EditText textPw;

    private String email = "";
    private String password = "";

    private Button button9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup1);

        firebaseAuth=FirebaseAuth.getInstance();
        button9 = (Button) findViewById(R.id.button9); //회원가입 버튼
        textId=(EditText) findViewById(R.id.textId);
        textPw=(EditText) findViewById(R.id.textPw);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser(textId.getText().toString(),textPw.getText().toString());
            }
        });
    }
    // 회원가입
    private void createUser(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 회원가입 성공
                            Toast.makeText(getApplicationContext(), "회원가입완료", Toast.LENGTH_SHORT).show();
                        } else {
                            // 회원가입 실패
                            Toast.makeText(getApplicationContext(), "회원가입실패", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}