package com.ECE1778A1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class IndexActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private String currentuser;
    Button btn_logout;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        View sideHeader = ((NavigationView)findViewById(R.id.nav_view)).getHeaderView(0);
//        TextView mainPage_t = (TextView)findViewById(R.id.nav_host_fragment); todo: fix this
        ImageView photo = (ImageView)sideHeader.findViewById(R.id.side_bar_user_image);

        try{
            currentuser = FirebaseAuth.getInstance().getCurrentUser().getEmail();
            ((TextView) sideHeader.findViewById(R.id.side_bar_email)).setText(currentuser);

            if(currentuser!=null){

//                if(!user.getImage().equals("sin imagen")){
////                    String url_image = "linklink"+user.getImage();
//                    try {
////                        Glide.with(this).load(url_image).into(photo);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_plaza,
                R.id.nav_logout)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
//        navigationView.setNavigationItemSelectedListener(
//                new NavigationView.OnNavigationItemSelectedListener() {
//                    @Override
//                    public boolean onNavigationItemSelected (MenuItem menuItem) {
//                        int id = menuItem.getItemId();
//                        if (id == R.id.nav_logout){
//                            logOut();
//                            return true;
//                        }
//                        return true;
//                    }
//                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //This function logout the current user and finish current activity
    private void logOut(){
        FirebaseAuth.getInstance().signOut();
        Intent backtoLogin = new Intent(getApplicationContext(),LoginActivity.class);
        backtoLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(backtoLogin);
    }

}


