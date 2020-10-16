package com.example.user.emergencyapps;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 9/30/2020.
 */

public class headerpage extends AppCompatActivity {
    private static final String TAG = "Profile";
    ArrayAdapter<String> adapter;
    DatabaseReference databaseReference, databaseReference1;
    List<String> itemlist;
    String uid;
    User users;
    FirebaseUser user;

    private TextView Username;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header);

        Username = findViewById(R.id.username);
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        itemlist = new ArrayList<>();
        user = FirebaseAuth.getInstance().getCurrentUser();

        uid = user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("profiledetails");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemlist.clear();
                try {
                    String fullname = dataSnapshot.child(uid).child("fullname").getValue(String.class);
                    itemlist.add(fullname);

                    Username.setText(fullname);

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
    }
}
