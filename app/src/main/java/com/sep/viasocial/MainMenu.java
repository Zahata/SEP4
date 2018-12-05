package com.sep.viasocial;

import android.content.Intent;
import android.support.annotation.NonNull;
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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sep.viasocial.AccountAuthentication.LoginActivity;
import com.sep.viasocial.Chat.ChatActivity;

public class MainMenu extends AppCompatActivity {
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private Intent backToLogin;
    private Intent groupChat;
    private FirebaseAuth mAuth;
    //comment
    //comment
    //comment


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        backToLogin = new Intent(MainMenu.this,LoginActivity.class);
        groupChat = new Intent(MainMenu.this,ChatActivity.class);

        mAuth = FirebaseAuth.getInstance();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
    public void onStart(){
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user == null){
            Intent start = new Intent(MainMenu.this, MainActivity.class);
            startActivity(start);
            finish();
        }
    }
    private void goToGroupChat(){
        startActivity(groupChat);
    }
    private void goToLogin(){
        startActivity(backToLogin);
        finish();
    }
        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logOut) {
            FirebaseAuth.getInstance().signOut();
            goToLogin();
            return true;
        }
        if (id == R.id.groupChat){
            goToGroupChat();
        }

        return super.onOptionsItemSelected(item);
    }
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    FriendsFragment tab1 = new FriendsFragment();
                    return tab1;

                case 1:
                    ChatsFragment tab2 = new ChatsFragment();
                    return tab2;

                case 2:
                    MyProfileFragment tab3 = new MyProfileFragment();
                    return tab3;

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
