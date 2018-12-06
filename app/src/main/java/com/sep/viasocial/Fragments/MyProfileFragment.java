package com.sep.viasocial.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.sep.viasocial.Model.Profile;
import com.sep.viasocial.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyProfileFragment extends Fragment {

    private CircleImageView profileImage;
    private TextView fullname;
    private TextView address;
    private TextView phone;
    private TextView programme;
    private TextView interests;

    private DatabaseReference databaseReference;
    private FirebaseUser user;


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
        String userID = user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
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
        });

        return rootView;
    }
}
