package com.sep.viasocial.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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
import com.sep.viasocial.Model.GroupChat;
import com.sep.viasocial.Model.Profile;
import com.sep.viasocial.R;

import java.util.ArrayList;
import java.util.List;

public class ChatsFragment extends Fragment {

    private RecyclerView recyclerView;

    private ProfileAdapter adapter;
    private List<Profile> users;
    private List<String>  chats;

    private FirebaseUser user;
    private DatabaseReference databaseReference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chats,container,false);

        recyclerView = view.findViewById(R.id.chatWithRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        chats = new ArrayList<>();

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Chats"); //.child(user.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                chats.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    GroupChat chat = snapshot.getValue(GroupChat.class);

                    if(chat.getSender().equals(user.getUid())){
                        chats.add(chat.getReceiver());
                    }
                    if(chat.getReceiver().equals(user.getUid())){
                        chats.add(chat.getSender());
                    }
                }
                readChats();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }

    private void readChats() {
        users = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users.clear();

                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Profile profile = snapshot.getValue(Profile.class);

                    // display a user from chats
                    for(String id: chats){
                        if(profile.getId().equals(id)){ //verify this one   .equalsIgnoreCase
                            if(users.size() != 0){
                                for(Profile otherProfile: users){
                                    if(!profile.getId().equals(otherProfile.getId())){
                                        users.add(profile);
                                    }
                                }
                            } else {
                                users.add(profile);
                            }
                        }
                    }
                }
                adapter = new ProfileAdapter(getContext(),users, true);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }
}
