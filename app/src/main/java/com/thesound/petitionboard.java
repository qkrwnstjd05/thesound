package com.thesound;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class petitionboard extends AppCompatActivity {

    private ArrayList<listDTO> mArrayList;
    private CustomAdapter mAdapter;
    private int count = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.petitionboard);



        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);


        mArrayList = new ArrayList<>();

        mAdapter = new CustomAdapter(mArrayList);
        mRecyclerView.setAdapter(mAdapter);


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("petition").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {

                        count++;
                        Map<String,Object> jsonData=document.getData();
                        Map<String,String> jsonString=(Map<String, String>) jsonData.get("info");
                        listDTO data = new listDTO(String.valueOf(count), jsonString.get("title"), String.valueOf(jsonString.get("like")),document.getId());

                        mArrayList.add(data);
                        mAdapter.notifyDataSetChanged();
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