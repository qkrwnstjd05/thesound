package com.thesound;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

public class seeanswer3 extends AppCompatActivity {

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

                        //Date asdf = new Date(jsonString.get("createDate").toString());
                        /*                 Sample                     */
                        //String date1 = jsonString.get("createDate").toString();
                        Log.d( "test", jsonData.toString());
                        Date date1=new Date();
                        petition_dto data = new petition_dto(Integer.valueOf(jsonData.get("like").toString()), Arrays.asList(jsonData.get("likes").toString()) ,jsonData.get("title").toString(),jsonData.get("description").toString(),jsonData.get("studentUid").toString(),jsonData.get("schoolUid").toString(),jsonData.get("answer").toString(),Boolean.valueOf(jsonData.get("isAnswer").toString()),jsonData.get("whoAnswer").toString(),date1);
                        petitiondetails.setText(data.getDescription());
                        teachersanswer.setText(data.getAnswer());
                        Petitionername.setText("익명");
                        petitiontitle.setText(data.getTitle());

                        //int like, List likes, String title, String description, String studentUid, String schoolUid,String answer, Boolean isAnswer, String whoAnswer, Date createDate

                    } else {
                        //Log.d(TAG, "No such document");
                    }
                } else {
                    Log.w("err", task.getException());
                }
            }
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
                            Toast.makeText(getApplicationContext(), "asdfgh", Toast.LENGTH_SHORT).show();
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
