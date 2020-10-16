package com.example.user.emergencyapps;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ViewMyReport extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
    private static final String TAG = "ViewMyReport";
    private ListView mListView;
    private ImageView Back;

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<reportcase> list;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_report);
        Back = findViewById(R.id.backicon);



        recyclerView = findViewById(R.id.myReportList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<reportcase>();


        reference = FirebaseDatabase.getInstance().getReference().child("reportcase");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    reportcase u = dataSnapshot1.getValue(reportcase.class);
                    list.add(u);
                }
                adapter = new MyAdapter(ViewMyReport.this, list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Opps, Something went wrong",Toast.LENGTH_LONG).show();
            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
       }



    public void showPopup(View v){
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.Profile:
                startActivity(new Intent(ViewMyReport.this, Profile.class));
                return true;
            case R.id.myreport:
                startActivity(new Intent(ViewMyReport.this, ViewMyReport.class));
                return true;
            case R.id.notification:
                startActivity(new Intent(ViewMyReport.this, UserNotification.class));
                return true;
            case R.id.share:
                Toast.makeText(getApplicationContext(), "Thanks for sharing", Toast.LENGTH_LONG).show();
                return true;
            case R.id.support:
                startActivity(new Intent(ViewMyReport.this, UserSupport.class));
                return true;
            case R.id.guide:
                startActivity(new Intent(ViewMyReport.this, UserGuide.class));
                return true;
            case R.id.logout:
                startActivity(new Intent(ViewMyReport.this, Loginpage.class));
                return true;
            default:
                return false;
        }
    }

}
