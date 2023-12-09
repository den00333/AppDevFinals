package com.example.appdev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView textName, textSection;
    private RecyclerView rv;
    private Button qbtn;
    private MissionVisionAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textName = findViewById(R.id.textName);
        textSection = findViewById(R.id.textSection);

        rv = findViewById(R.id.rView);

        String name = "Dinielle Cordon";
        String section = "BSCS - DS 3A";
        textName.setText(name);
        textSection.setText(section);

        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MissionVisionAdapter(getMissionVisionData());
        rv.setAdapter(adapter);

        qbtn = findViewById(R.id.button4);
        qbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getQuestion();
            }
        });


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
}