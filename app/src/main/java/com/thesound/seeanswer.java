package com.thesound;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

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

        firebaseAuth=firebaseAuth.getInstance();


        petitiondetails= (TextView)findViewById(R.id.petitiondetails);
        Teachersname=(TextView) findViewById(R.id.Teachersname);
        teachersanswer=(TextView) findViewById(R.id.teachersanswer);
        Petitionername=(TextView) findViewById(R.id.Petitionername);
        petitiontitle=(TextView) findViewById(R.id.petitiontitle);
    }

}