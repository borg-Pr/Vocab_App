package com.borg.vocab_app_3;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


public class BottomNavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        showFragment(new ActiveVocabFragment());

        BottomNavigationView navView = findViewById(R.id.nav_view);

        navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_active:
                        showFragment(new ActiveVocabFragment());
                        break;
                    case R.id.navigation_in_process:
                        showFragment(new InProcessVocabFragment());
                        break;
                    case R.id.navigation_unknown_words:
                        showFragment(new UnknownWordsFragment());
                        break;
                }

                return true;
            }
        });
    }

    private void showFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, fragment)
                .commit();
    }

}






