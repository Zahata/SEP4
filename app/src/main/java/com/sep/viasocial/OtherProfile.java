package com.sep.viasocial;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sep.viasocial.Model.Profile;
import com.sep.viasocial.R;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
public class OtherProfile extends AppCompatActivity {
    private CircleImageView profileImage;
    private TextView fullname;
    private TextView address;
    private TextView phone;
    private TextView programme;
    private TextView interests;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_profile);
        String userid = getIntent().getStringExtra("userid");
        profileImage = findViewById(R.id.profileImageOther);
        fullname = findViewById(R.id.fullNameOther);
        address = findViewById(R.id.addressOther);
        phone = findViewById(R.id.phoneOther);
        programme = findViewById(R.id.studyProgrammeOther);
        interests = findViewById(R.id.interestsOther);
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userid);
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
    }
}
