package com.sep.viasocial;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyProfileFragment extends Fragment {

    private CircleImageView profileImage;
    private TextView fullname;
    private TextView address;
    private TextView phone;
    private TextView programme;
    private TextView interests;

    private FirebaseUser user;
    private String userID;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseReference;
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

        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();

        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference().child("Users").child(userID);

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue().toString();
                String addressText = dataSnapshot.child("address").getValue().toString();
                String phoneText = dataSnapshot.child("phone").getValue().toString();
                String programmeText = dataSnapshot.child("programme").getValue().toString();
                String interestsText = dataSnapshot.child("interests").getValue().toString();

                fullname.setText(name);
                address.setText(addressText);
                phone.setText(phoneText);
                programme.setText(programmeText);
                interests.setText(interestsText);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });

        return rootView;
    }

}
