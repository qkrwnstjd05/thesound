package com.thesound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class seeanswer extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private TextView petitiontitle;
    private TextView Petitionername;
    private TextView petitiondetails;
    private TextView Teachersname;
    private TextView teachersanswer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeanswer);

        Intent intent=getIntent();
        String key=intent.getStringExtra("key");


        firebaseAuth=firebaseAuth.getInstance();


        petitiondetails= (TextView)findViewById(R.id.petitiondetails);
        Teachersname=(TextView) findViewById(R.id.Teachersname);
        teachersanswer=(TextView) findViewById(R.id.teachersanswer);
        Petitionername=(TextView) findViewById(R.id.Petitionername);
        petitiontitle=(TextView) findViewById(R.id.petitiontitle);

        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("petition").document(key);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Map<String,Object> jsonData=document.getData();
                        Map<String,Object> jsonString=(Map<String, Object>) jsonData.get("info");
                        Toast.makeText(getApplicationContext(), jsonString.toString(), Toast.LENGTH_LONG).show();
                        //Date asdf = new Date(jsonString.get("createDate").toString());
                        /*                 Sample                     */
                        //String date1 = jsonString.get("createDate").toString();
                        Date date1=new Date();
                        petition_dto data = new petition_dto(Integer.valueOf(jsonString.get("like").toString()), Arrays.asList(jsonString.get("likes").toString()) ,jsonString.get("title").toString(),jsonString.get("description").toString(),jsonString.get("studentUid").toString(),jsonString.get("schoolUid").toString(),jsonString.get("answer").toString(),Boolean.valueOf(jsonString.get("isAnswer").toString()),jsonString.get("whoAnswer").toString(),date1);
                        petitiondetails.setText(data.getDescription());
                        Teachersname.setText(data.getWhoAnswer());
                        teachersanswer.setText(data.getAnswer());
                        Petitionername.setText(data.getStudentUid());
                        petitiondetails.setText(data.getTitle());

                        //int like, List likes, String title, String description, String studentUid, String schoolUid,String answer, Boolean isAnswer, String whoAnswer, Date createDate

                    } else {
                        //Log.d(TAG, "No such document");
                    }
                } else {
                    Log.w("err", task.getException());
                }
            }
        });

    }

}