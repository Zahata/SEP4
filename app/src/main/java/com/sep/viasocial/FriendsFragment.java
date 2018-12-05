package com.sep.viasocial;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sep.viasocial.Adapter.ProfileAdapter;

import java.util.ArrayList;
import java.util.List;

public class FriendsFragment extends Fragment {

    //private Toolbar mToolbar;
    private RecyclerView mUsersList;

    private ProfileAdapter profileAdapter;
    private List<Profile> mProfiles;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_friends, container, false);

        mUsersList = view.findViewById(R.id.users_list);
        mUsersList.setHasFixedSize(true);
        mUsersList.setLayoutManager(new LinearLayoutManager(getContext()));

        mProfiles = new ArrayList<>();

        readProfiles();

        return view;
    }

    private void readProfiles() {
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mProfiles.clear();

                //if (search_users.getText().toString().equals("")) {
                //mUsers.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (!snapshot.getKey().equals(firebaseUser.getUid())) {
                        Profile profile = snapshot.getValue(Profile.class);
                        mProfiles.add(profile);
                    }
                }

                profileAdapter = new ProfileAdapter(getContext(), mProfiles);
                mUsersList.setAdapter(profileAdapter);
                //}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
