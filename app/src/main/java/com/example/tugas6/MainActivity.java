package com.example.tugas6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import com.google.android.material.navigation.NavigationView;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tugas6.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    // tugas shared
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    // tugas recycle view
    RecyclerView recylerView;
    String s1[], s2[], s3[];
    int images[] = {R.drawable.images, R.drawable.sepatu, R.drawable.foto};

    private DrawerLayout dl;
    private ActionBarDrawerToggle abut;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // tugas MyWorker
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot()); final OneTimeWorkRequest request = new
                OneTimeWorkRequest.Builder(MyWorker.class).build(); binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkManager.getInstance().enqueueUniqueWork("Notifikasi", ExistingWorkPolicy.REPLACE, request);
            }
        });
        // tugas shared
        preferences = getSharedPreferences("AndroidHiveLogin", 0);
        editor = preferences.edit();

    // tugas recycle view

        recylerView = findViewById(R.id.recyclerView);
        s1 = getResources().getStringArray(R.array.Sepatu);
        s2 = getResources().getStringArray(R.array.star);
        s3 = getResources().getStringArray(R.array.harga);
        SepatuAdapter appAdapter = new SepatuAdapter(this, s1, s2, s3, images);
        recylerView.setAdapter(appAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recylerView.setLayoutManager(layoutManager);
        recylerView.setItemAnimator(new DefaultItemAnimator());
        dl = (DrawerLayout) findViewById(R.id.dl);
        abut = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);
        abut.setDrawerIndicatorEnabled(true);
        dl.addDrawerListener(abut);
        abut.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    Intent a = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(a);
                } else if (id == R.id.nav_message) {
                    Intent a = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(a);
                } else if (id == R.id.nav_profile) {
                    Intent a = new Intent(MainActivity.this, MainActivity3.class);
                    startActivity(a);
                }else if (id == R.id.nav_api) {
                    Intent a = new Intent(MainActivity.this, MainActivity4.class);
                    startActivity(a);
                }else if (id == R.id.nav_logout) {
                    editor.clear();
                    editor.commit();
                    Intent a = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(a);
                }
                return true;
            }
        });
    }
    @Override
        public boolean onOptionsItemSelected (@NonNull MenuItem item){
            return abut.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
        }
    }





