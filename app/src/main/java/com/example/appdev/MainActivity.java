package com.example.appdev;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import yuku.ambilwarna.AmbilWarnaDialog;

public class MainActivity extends AppCompatActivity {
    private TextView textName, textSection;
    private RecyclerView rv;
    private Button qbtn;
    private MissionVisionAdapter adapter;
    private View colorPreview;
    private RelativeLayout rl;
    int defaultColor;
    private TextView n1, n2;
    int num1 = 5;
    int num2 = 10;
    private static final String PREFS_NAME = "MyPrefs";
    private static final String SELECTED_COLOR_KEY = "selectedColor";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        n1 = findViewById(R.id.nnumber1);
        n2 = findViewById(R.id.nnumber2);
        num1 += 2;
        num2 += 2;
        n1.setText("num1:" + num1);
        n2.setText("num2:" + num2);
        Toast.makeText(this, "Dinielle Cordon", Toast.LENGTH_SHORT).show();
        int savedColor = getSavedColor();
        updateBackgroundColor(savedColor);

        textName = findViewById(R.id.textName);
        textSection = findViewById(R.id.textSection);

        rv = findViewById(R.id.rView);

        rl = findViewById(R.id.rLayout);
        defaultColor = ContextCompat.getColor(this, com.google.android.material.R.color.design_default_color_primary);

        FloatingActionButton fabColor = findViewById(R.id.fabChangeColor);
        fabColor.setOnClickListener(v -> showColorPicker());

        String name = "Dinielle Cordon";
        String section = "BSCS - DS 3A";
        textName.setText(name);
        textSection.setText(section);

        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MissionVisionAdapter(getMissionVisionData());
        rv.setAdapter(adapter);

        qbtn = findViewById(R.id.button4);
        qbtn.setOnClickListener(v -> getQuestion());


    }

    protected void onPause(){
        super.onPause();
        num1 += 2;
        num2 += 2;
        n1.setText("num1:" + num1);
        n2.setText("num2:" + num2);
    }

    private List<MissionVissionItem>getMissionVisionData(){
        List<MissionVissionItem> data = new ArrayList<>();
        data.add(new MissionVissionItem("Mission", "Laguna University is committed to produce academically prepared and\n" +
                "technically skilled individuals who are socially and morally upright.\n"));
        data.add(new MissionVissionItem("Vision", "Laguna University shall be a socially responsive educational institution of\n" +
                "choice providing holistically developed individuals in the Asia-Pacific Region.\n"));
        return data;
    }

    private void getQuestion(){
        questionFragment qf = new questionFragment();
        qf.show(getSupportFragmentManager(), "Question");
    }

    private void showColorPicker(){
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(this, defaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                defaultColor = color;
                rl.setBackgroundColor(defaultColor);
                saveSelectedColor(defaultColor);
            }
        });
        colorPicker.show();
    }

    private void saveSelectedColor(int color) {
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putInt(SELECTED_COLOR_KEY, color);
        editor.apply();
    }

    private int getSavedColor() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return prefs.getInt(SELECTED_COLOR_KEY, Color.WHITE);
    }

    private void updateBackgroundColor(int color) {
        findViewById(R.id.rLayout).setBackgroundColor(color);
    }
}