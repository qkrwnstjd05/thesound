package com.thesound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
    private Spinner textSchoolname;

    private String email = "";
    private String password = "";

    private Button button9;
    private String chkSpinner="";
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
        textSchoolname=(Spinner)findViewById(R.id.stu_sch_name);

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chkSpinner.equals("")) {
                    Toast.makeText(getApplicationContext(), "학교를 선택해주세요", Toast.LENGTH_SHORT).show();
                }
                else{
                    createUser(textId.getText().toString(), textPw.getText().toString());
                }
            }
        });
        ArrayList<String> paths = new ArrayList<>();
        ArrayList<String> uids = new ArrayList<>();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference productRef = db.collection("schoolUser");
        productRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        try {
                            JSONObject jsonarr=new JSONObject(document.get("schoolDTO").toString());
                            paths.add(jsonarr.get("sch_name").toString());
                            uids.add(document.getId());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } else {

                }
            }
        });
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,paths);
        textSchoolname.setAdapter(arrayAdapter);
        textSchoolname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chkSpinner=uids.get(position);
                Log.d("select",chkSpinner);
                textSchoolname.setSelection(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
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
                            String stu_sch_name=chkSpinner;

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