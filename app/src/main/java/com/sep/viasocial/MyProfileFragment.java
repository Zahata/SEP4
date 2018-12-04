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

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser user;
    private String userID;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener childEventListener;
    private FirebaseStorage storage;
    private StorageReference storageReference;


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

        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
       /* childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Profile profile = dataSnapshot.child("Users").child(userID).getValue(Profile.class);
                String image = profile.getPhotoURL();
                Picasso.get().load(image).into(profileImage);

                String name = profile.getFullName();
                fullname.setText(name);

                String address = profile.getAddress();
                fullname.setText(address);

                String phone = profile.getPhone();
                fullname.setText(phone);

                String studyProgramme = profile.getStudyProgramme();
                fullname.setText(studyProgramme);

                String interests = profile.getInterests();
                fullname.setText(interests);
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Profile profile = dataSnapshot.child("Users").child(userID).getValue(Profile.class);

                String image = profile.getPhotoURL();
                Picasso.get().load(image).into(profileImage);

                String name = profile.getFullName();
                fullname.setText(name);

                String address = profile.getAddress();
                fullname.setText(address);

                String phone = profile.getPhone();
                fullname.setText(phone);

                String studyProgramme = profile.getStudyProgramme();
                fullname.setText(studyProgramme);

                String interests = profile.getInterests();
                fullname.setText(interests);
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
*/
       /* mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    userID = user.getUid();
                    mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);
                    Toast.makeText(MyProfileFragment.this.getActivity(),"User" + userID,Toast.LENGTH_SHORT).show();


                }
                else{
                    startActivity(goBackToLogin);
                }
            }
        };
*/

        //Toast.makeText(MyProfileFragment.this.getActivity(),"User" + userID,Toast.LENGTH_SHORT).show();



      /*  mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
               *//* String image = dataSnapshot.child("photoURL").getValue().toString();
                String name = dataSnapshot.child("fullName").getValue().toString();
                String addressText = dataSnapshot.child("address").getValue().toString();
                String phoneText = dataSnapshot.child("phone").getValue().toString();
                String programmeText = dataSnapshot.child("studyProgramme").getValue().toString();
                String interestsText = dataSnapshot.child("interests").getValue().toString();*//*

                Profile profile = dataSnapshot.getValue(Profile.class);

                Picasso.get().load(profile.getPhotoURL()).into(profileImage);
                fullname.setText(profile.getFullName());
                address.setText(profile.getAddress());
                phone.setText(profile.getPhone());
                programme.setText(profile.getStudyProgramme());
                interests.setText(profile.getInterests());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });*/
        return rootView;
    }

    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    public void onPause(){
        super.onPause();
        mAuth.removeAuthStateListener(mAuthListener);
    }
}
