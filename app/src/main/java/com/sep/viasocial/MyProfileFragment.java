package com.sep.viasocial;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sep.viasocial.AccountAuthentication.LoginActivity;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyProfileFragment extends Fragment {

    private CircleImageView profileImage;
    private TextView fullname;
    private TextView address;
    private TextView phone;
    private TextView programme;
    private TextView interests;
    private Intent goBackToLogin;

    //private FirebaseDatabase firebaseDatabase;
   // private FirebaseAuth mFirebaseAuth;
   // private FirebaseAuth.AuthStateListener authStateListener;
    private DatabaseReference databaseReference;
    private FirebaseUser user;
    //private String userID;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_profile, container, false);

        profileImage = rootView.findViewById(R.id.profileImage);
        fullname = rootView.findViewById(R.id.fullName);
        address = rootView.findViewById(R.id.address);
        phone = rootView.findViewById(R.id.phone);
        programme = rootView.findViewById(R.id.studyProgramme);
        interests = rootView.findViewById(R.id.interests);
        goBackToLogin = new Intent(MyProfileFragment.this.getActivity(),LoginActivity.class);

        user = FirebaseAuth.getInstance().getCurrentUser();
        String userID = user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                /*Toast.makeText(MyProfileFragment.this.getActivity(),dataSnapshot.toString(),Toast.LENGTH_LONG).show();
                String name = dataSnapshot.child("fullName").getValue().toString();
                fullname.setText(name);*/
                Profile profile = dataSnapshot.getValue(Profile.class);
                fullname.setText(profile.getFullName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

       /* databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Profile profile = dataSnapshot.getValue(Profile.class);

                // Picasso.get().load(profile.getPhotoURL()).into(profileImage);
                fullname.setText(profile.getFullName());
                address.setText(profile.getAddress());
                phone.setText(profile.getPhone());
                programme.setText(profile.getStudyProgramme());
                interests.setText(profile.getInterests());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
        /*mFirebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();*/

        //FirebaseUser user = mFirebaseAuth.getCurrentUser();


        /*authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    userID = user.getUid();

                    //Toast.makeText(MyProfileFragment.this.getActivity(),"User logged: " + user.getUid(), Toast.LENGTH_SHORT).show();
                }
                else{
                    startActivity(goBackToLogin);
                }
            }
        };*/


        return rootView;
    }

   /* public void onStart(){
        super.onStart();
        mFirebaseAuth.addAuthStateListener(authStateListener);
    }
    public void onPause(){
        super.onPause();
        mFirebaseAuth.removeAuthStateListener(authStateListener);
    }*/
}
