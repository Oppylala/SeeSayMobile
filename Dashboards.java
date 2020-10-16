package com.example.user.emergencyapps;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Dashboards extends AppCompatActivity {

    private static final String TAG ="Dashboard";
    ImageView RoadAccidentReport,FireIncident,KidnappingReport, ArmedRobberyReport, DrugAbuseReport, MurderCaseReport, SexAbuseReport, Medical;
    TextView Road,Fire,Kidnap, Armed, Drug, Murder, Sex, Username;

    private DatabaseReference mRef;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String userID;

    DatabaseReference databaseReference;
    FirebaseUser user;
    List<String> itemlist;
    String uid;

    private CircleImageView Userimage;

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboards);

        RoadAccidentReport = findViewById(R.id.RoadAccident);
        FireIncident = findViewById(R.id.FireIncident);
        KidnappingReport = findViewById(R.id.Kidnapping);
        ArmedRobberyReport = findViewById(R.id.ArmedRobbery);
        DrugAbuseReport = findViewById(R.id.DrugAbuse);
        MurderCaseReport = findViewById(R.id.MurderReport);
        SexAbuseReport = findViewById(R.id.SexualAbuse);
        Medical = findViewById(R.id.Medical);

        Road = findViewById(R.id.accident);
        Fire = findViewById(R.id.fire);
        Kidnap = findViewById(R.id.kidnap);
        Armed = findViewById(R.id.armed);
        Drug = findViewById(R.id.drug);
        Murder = findViewById(R.id.murder);
        Sex = findViewById(R.id.sexual);
        Username = findViewById(R.id.dashUsername);
        Userimage = findViewById(R.id.profileImage);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();


        user = FirebaseAuth.getInstance().getCurrentUser();

        uid = user.getUid();
        itemlist = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("user");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try {
                    String email = dataSnapshot.child(uid).child("username").getValue(String.class);
                    String imageUser = dataSnapshot.child(uid).child("image").getValue(String.class);

                    Username.setText(email);
                    Picasso.get().load(imageUser).into(Userimage);

                }catch (Exception ex){
                    Log.d(TAG,"Exception caught") ;
                    ex.printStackTrace();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG,"Error Occur");

            }
        });


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    //user signed in
                    Log.d(TAG,"onAuthStateChanged:Sign_in:"+user.getUid());

                }else{
                    //sign out user
                    Log.d(TAG,"onAuthStateChanged:sign_out");
                    toastMessage("Successfully sign out");
                }
            }
        };

        RoadAccidentReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String crime = Road.getText().toString().trim();
                Intent intent = new Intent(Dashboards.this, Reports.class);
                intent.putExtra("crime", crime);
                startActivity(intent);
            }
        });
        FireIncident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String crime = Fire.getText().toString().trim();
                Intent intent = new Intent(Dashboards.this, Reports.class);
                intent.putExtra("crime", crime);
                startActivity(intent);
            }
        });
        KidnappingReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String crime = Kidnap.getText().toString().trim();
                Intent intent = new Intent(Dashboards.this, Reports.class);
                intent.putExtra("crime", crime);
                startActivity(intent);
            }
        });
        ArmedRobberyReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String crime = Armed.getText().toString().trim();
                Intent intent = new Intent(Dashboards.this, Reports.class);
                intent.putExtra("crime", crime);
                startActivity(intent);
            }
        });
        SexAbuseReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String crime = Sex.getText().toString().trim();
                Intent intent = new Intent(Dashboards.this, Reports.class);
                intent.putExtra("crime", crime);
                startActivity(intent);
            }
        });
        MurderCaseReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String crime = Murder.getText().toString().trim();
                Intent intent = new Intent(Dashboards.this, Reports.class);
                intent.putExtra("crime", crime);
                startActivity(intent);
            }
        });

        DrugAbuseReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String crime = Drug.getText().toString().trim();
                Intent intent = new Intent(Dashboards.this, Reports.class);
                intent.putExtra("crime", crime);
                startActivity(intent);
            }
        });

        Medical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String crime = Drug.getText().toString().trim();
                Intent intent = new Intent(Dashboards.this, Medical.class);
                intent.putExtra("crime", crime);
                startActivity(intent);
            }
        });

        dl = findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.profile:
                        startActivity(new Intent(Dashboards.this, Profile.class));
                        return true;
                    case R.id.settings:
                        startActivity(new Intent(Dashboards.this, EditProfile.class));
                        return true;
                    case R.id.logout:
                        startActivity(new Intent(Dashboards.this, Loginpage.class));
                        return true;
                    default:
                        return false;
                }

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    private void toastMessage(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }
}
