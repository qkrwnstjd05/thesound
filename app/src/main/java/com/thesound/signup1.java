package com.thesound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class signup1 extends AppCompatActivity {

    // 파이어베이스 인증 객체 생성
    private FirebaseAuth firebaseAuth;

    // 이메일과 비밀번호
    private EditText textId;
    private EditText textPw;
    private EditText textyear;
    private EditText textclassroom;
    private EditText textnumber;
    private EditText textname;
    private EditText textPwChk;
    private EditText textSchoolname;

    private String email = "";
    private String password = "";

    private Button button9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup1);

        firebaseAuth=FirebaseAuth.getInstance();

        button9 = (Button) findViewById(R.id.button9); //회원가입 버튼
        textId=(EditText) findViewById(R.id.stu_email);
        textPw=(EditText) findViewById(R.id.stu_pw);
        textPwChk=(EditText) findViewById(R.id.stu_pw_chk);
        textyear=(EditText) findViewById(R.id.stu_sch_year);
        textclassroom=(EditText) findViewById(R.id.stu_sch_classroom);
        textnumber=(EditText) findViewById(R.id.stu_sch_number);
        textname=(EditText) findViewById(R.id.stu_name);
        textSchoolname=(EditText)findViewById(R.id.stu_sch_name);

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
                            String uid=firebaseAuth.getCurrentUser().getUid();
                            String year=textyear.getText().toString();
                            String classroom=textclassroom.getText().toString();
                            String number=textnumber.getText().toString();
                            String name=textname.getText().toString();
                            String stu_sch_name=textSchoolname.getText().toString();
                            Login_info login_info=new Login_info(year,classroom,number,name,stu_sch_name);
                            FirebaseFirestore db=FirebaseFirestore.getInstance();
                            db.collection("studentUser").document(uid).set(login_info)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(getApplicationContext(), "회원가입완료", Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(signup1.this, login.class);
                                            startActivity(i);
                                        }
                                    });
                        } else {
                            // 회원가입 실패
                            Toast.makeText(getApplicationContext(), "회원가입실패", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}