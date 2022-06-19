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
import android.widget.ImageView;
import android.widget.TextView;

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

public class petitionboard3 extends AppCompatActivity{
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
                        //Map<String,String> jsonString=(Map<String, String>) jsonData.get("title");
                        listDTO data = new listDTO(String.valueOf(count), jsonData.get("title").toString(), String.valueOf(jsonData.get("like")),document.getId());
                        Log.d("datatadat",data.toString());
                        mArrayList.add(0,data);
                        mAdapter.notifyDataSetChanged();
                    }
                } else {
                    Log.w("err", task.getException());
                }
            }
        });
        BottomNavigationView bottomNavigation  = findViewById(R.id.bottomNav);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        MenuItem item = bottomNavigation.getMenu().findItem(R.id.go_to_all_bulletin_board);
        item.setChecked(true);
    }


    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.go_to_search3:
                            Intent intent1=new Intent(getApplicationContext(),surch3.class);
                            startActivity(intent1);
                            return true;
                        case R.id.go_to_all_bulletin_board3:
                            Intent intent=new Intent(getApplicationContext(),petitionboard3.class);
                            startActivity(intent);
                            return true;
                        case R.id.go_to_home3:
                            Intent intent2=new Intent(getApplicationContext(),main3.class);
                            startActivity(intent2);
                            return true;
                        case R.id.go_to_suggestionan_swered:
                            Intent intent3=new Intent(getApplicationContext(),our_school_bullentin_board3.class);
                            startActivity(intent3);
                            return true;
                        case R.id.view_authentication_requests:
                            Intent intent4=new Intent(getApplicationContext(),asksee.class);
                            startActivity(intent4);
                            return true;
                    }
                    return false;
                }
            };

}
