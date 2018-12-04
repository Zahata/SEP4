package com.sep.viasocial;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.sep.viasocial.Chat.ChatMessage;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class SetupProfile extends AppCompatActivity {

    private FirebaseUser user;
    private String userID;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    private CircleImageView profileImage;
    private EditText nameText;
    private EditText addressText;
    private EditText phoneText;
    private EditText programmeText;
    private EditText interestsText;
    private Button saveButton;
    private Intent mainMenu;

    private Intent pickImage;
    private static final int RC_PHOTO_PICKER = 2;
    private Uri selectedImageUri;
    private  Uri downloadUrl;

    public SetupProfile() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_profile);

        mainMenu = new Intent(this,MainMenu.class);

        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();

        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference().child("Users");

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference().child("Profile images");

        profileImage = findViewById(R.id.profile_image);
        nameText = findViewById(R.id.fullnameEdit);
        addressText = findViewById(R.id.addressEdit);
        phoneText = findViewById(R.id.phoneEdit);
        programmeText = findViewById(R.id.programmeEdit);
        interestsText = findViewById(R.id.interestsEdit);
        saveButton = findViewById(R.id.saveInfoButton);

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage = new Intent();
                pickImage.setType("image/*");
                pickImage.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(pickImage, "Complete action using"), RC_PHOTO_PICKER);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null) {
                    Profile profile = new Profile(downloadUrl.toString(), nameText.getText().toString(), addressText.getText().toString(),
                            phoneText.getText().toString(), programmeText.getText().toString(), interestsText.getText().toString());
                    mDatabaseReference.push().setValue(profile);
                    Toast.makeText(SetupProfile.this,"Successful",Toast.LENGTH_SHORT).show();
                    startActivity(mainMenu);
                }

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_PHOTO_PICKER && resultCode == RESULT_OK) {
            selectedImageUri = data.getData();

            // Get a reference to store file at chat_photos/<FILENAME>
            final StorageReference photoRef = storageReference.child(selectedImageUri.getLastPathSegment());

            // Upload file to Firebase Storage
            photoRef.putFile(selectedImageUri)
                    .addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // When the image has successfully uploaded, we get its download URL
                            photoRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                     downloadUrl = uri;
                                    Picasso.get().load(downloadUrl).into(profileImage);
                                }

                            });
                        }
                    });
        }
    }

}
