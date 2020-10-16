package com.example.user.emergencyapps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfile extends AppCompatActivity {
    private static final String TAG = "EditProfile";

   private Button ChangeProfile, btnSave, btnClose;
    private EditText userEmail, userName, userPhone;
    private CircleImageView profileImageview;
    private ImageView Back;
    private TextView Logout;

    private Uri imageUri;
    private String myUri ="";
    private FirebaseAuth mAuth;
    private StorageTask uploadTask;
    private StorageReference storageProfilepicsRef;
    private DatabaseReference databaseReference;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        ChangeProfile = findViewById(R.id.change);
        btnSave = findViewById(R.id.btnRegister);
        btnClose = findViewById(R.id.btnClose);

        userName = findViewById(R.id.username);
        userPhone = findViewById(R.id.phone);
        userEmail = findViewById(R.id.email);
        profileImageview = findViewById(R.id.profileimage);


        Back = findViewById(R.id.backicon);
        Logout = findViewById(R.id.logout);



        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("user");
        storageProfilepicsRef = FirebaseStorage.getInstance().getReference().child("image");


        getUserInfo();
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditProfile.this, Loginpage.class));
            }
        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditProfile.this, Dashboards.class));
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  uploadProfileImage();
                updateUsers();
            }
        });

        ChangeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               CropImage.activity(imageUri).setAspectRatio(1,1).start(EditProfile.this);

            }
        });

        }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data !=null){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            imageUri = result.getUri();
            profileImageview.setImageURI(imageUri);
        }else{
            Toast.makeText(getApplicationContext(),"Error, Try Again",Toast.LENGTH_LONG).show();
        }
    }
    private void getUserInfo() {
        databaseReference.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            if(dataSnapshot.exists() && dataSnapshot.getChildrenCount()>0){
                String email = dataSnapshot.child("email").getValue(String.class);
                String phone = dataSnapshot.child("phone").getValue(String.class);
                String username = dataSnapshot.child("username").getValue(String.class);
                userName.setText(username);
                userPhone.setText(phone);
                userEmail.setText(email);
                    if(dataSnapshot.hasChild("image")){
                        String image = dataSnapshot.child("image").getValue().toString();
                        Picasso.get().load(image).into(profileImageview);

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

   private void updateUsers(){
    final ProgressDialog progressDialog = new ProgressDialog(this);
    progressDialog.setTitle("SeeSay");
    progressDialog.setMessage("Please wait while updating your profile");
    progressDialog.show();
    new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            Intent intent=new Intent(EditProfile.this,Mainpage.class);
            startActivity(intent);

            databaseReference.child(mAuth.getCurrentUser().getUid()).child("image").setValue(imageUri.toString());
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(),"Your Profile has been uploaded Successfully",Toast.LENGTH_LONG).show();
            startActivity(new Intent(EditProfile.this, Dashboards.class));
        }
    },8000);






}

}

