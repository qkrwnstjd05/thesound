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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class signup2 extends AppCompatActivity {


    private FirebaseAuth firebaseAuth;
    private EditText sch_name;
    private EditText sch_address;
    private EditText sch_email;
    private EditText sch_teacher;
    private EditText sch_number;
    private EditText sch_pw;
    private EditText sch_pw_chk;

    private Button Button15;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);

        firebaseAuth= FirebaseAuth.getInstance();

        sch_name=(EditText) findViewById(R.id.sch_name);
        sch_address=(EditText) findViewById(R.id.sch_address);
        sch_email=(EditText) findViewById(R.id.sch_email);
        sch_teacher=(EditText) findViewById(R.id.sch_teacher);
        sch_number=(EditText) findViewById(R.id.sch_number);
        sch_pw=(EditText) findViewById(R.id.sch_pw);
        sch_pw_chk=(EditText) findViewById(R.id.sch_pw_chk);
        Button15=(Button) findViewById(R.id.button15);

        Button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=sch_email.getText().toString();
                String pw=sch_pw.getText().toString();
                String pw_chk=sch_pw_chk.getText().toString();
                if(pw.equals(pw_chk)){
                    createUser(id,pw);
                }
                else{
                    Toast.makeText(getApplicationContext(), "비밀번호 다르다ㅇㅇ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    class SchoolDTO{
        String sch_name;
        String sch_address;
        String sch_email;
        String sch_teacher;
        String sch_number;
        String sch_uid;
        String sch_type;

        public SchoolDTO(String sch_name,String sch_address,String sch_email, String sch_teacher,String sch_number,String sch_uid){
            this.sch_name=sch_name;
            this.sch_address=sch_address;
            this.sch_email=sch_email;
            this.sch_teacher=sch_teacher;
            this.sch_number=sch_number;
            this.sch_uid=sch_uid;
            this.sch_type="sch";
        }
        public HashMap getSchoolDTO(){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("sch_name",sch_name);
            map.put("sch_address",sch_address);
            map.put("sch_email",sch_email);
            map.put("sch_teacher",sch_teacher);
            map.put("sch_number",sch_number);
            map.put("sch_uid",sch_uid);
            map.put("sch_type",sch_type);
            return map;
        }
    }
    private void createUser(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String uid=firebaseAuth.getCurrentUser().getUid();
                            String strName=sch_name.getText().toString();
                            String strAdd=sch_address.getText().toString();
                            String strTeacher=sch_teacher.getText().toString();
                            String strNumber=sch_number.getText().toString();
                            SchoolDTO schDTO=new SchoolDTO(strName,strAdd,email,strTeacher,strNumber,uid);

                            FirebaseFirestore db=FirebaseFirestore.getInstance();
                            db.collection("schoolUser").document().set(schDTO)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(getApplicationContext(), "회원가입완료", Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(signup2.this, login.class);
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