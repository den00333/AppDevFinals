package com.example.appdev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        String titleName = "nothing";
        Intent i = getIntent();
        if(i != null && i.hasExtra("username")){
            titleName = i.getStringExtra("username");
        }
        Toolbar tlb = findViewById(R.id.toolbar);
        setSupportActionBar(tlb);
        getSupportActionBar().setTitle(String.format("Welcome, %s!", titleName));

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.flFrag, new GameFragment()).commit();
        }

        BottomNavigationView bnv = findViewById(R.id.bottom_navigation);
        bnv.setOnItemSelectedListener(
                new BottomNavigationView.OnItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        int itemId = item.getItemId();
                        Fragment selectedFrag = null;
                        if (itemId == R.id.navGame) {
                            selectedFrag = new GameFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.flFrag, selectedFrag).commit();
                            Toast.makeText(getApplicationContext(), "game", Toast.LENGTH_SHORT).show();
                            return true;
                        } else if (itemId == R.id.navSummary) {
                            selectedFrag = new SummaryFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.flFrag, selectedFrag).commit();
                            Toast.makeText(getApplicationContext(), "Summary", Toast.LENGTH_SHORT).show();
                            return true;
                        }

                        return false;
                    }
                }
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.Settings) {
            Toast.makeText(getApplicationContext(), "Settings clicked", Toast.LENGTH_LONG).show();

            return true;
        } else if (itemId == R.id.Exit) {
            Toast.makeText(getApplicationContext(), "Exiting", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}