package com.sep.viasocial;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sep.viasocial.AccountAuthentication.LoginActivity;
import com.sep.viasocial.Fragments.ChatsFragment;
import com.sep.viasocial.Fragments.UsersFragment;
import com.sep.viasocial.Fragments.MyProfileFragment;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    private Intent backToLogin;
    private Intent groupChat;

    private FirebaseUser mUser;
    private DatabaseReference reference;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        backToLogin = new Intent(MainActivity.this,LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        groupChat = new Intent(MainActivity.this,ChatActivity.class);

        mUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logOut) { //change this code beacuse app crashes
            FirebaseAuth.getInstance().signOut();
            Status("offline");
            startActivity(backToLogin);
            finish(); //check this one
            return true;
        }
        if (id == R.id.groupChat){
            startActivity(groupChat);
            return true; //check this one
        }

        //return super.onOptionsItemSelected(item);
        return false;
    }

    public void onStart(){
        super.onStart();
        //FirebaseUser user = mAuth.getCurrentUser();
        if(mUser == null){
            Intent start = new Intent(MainActivity.this, StartActivity.class);
            startActivity(start);
            finish();
        }
    }

    /*private void goToGroupChat(){
        startActivity(groupChat);
    }*/
    /*private void goToLogin(){
        startActivity(backToLogin);
        finish();
    }*/

    class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new UsersFragment();

                case 1:
                    return new ChatsFragment();

                case 2:
                    return new MyProfileFragment();

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    public void Status(String status){
        reference = FirebaseDatabase.getInstance().getReference("Users").child(mUser.getUid());

        HashMap<String, Object> map = new HashMap<>();
        map.put("status",status);

        reference.updateChildren(map);
    }

    protected void onResume(){
        super.onResume();
        Status("online");
    }

    protected void onPause(){
        super.onPause();
        Status("offline");
    }
}
