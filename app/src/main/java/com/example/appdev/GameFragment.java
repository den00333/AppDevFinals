package com.example.appdev;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GameFragment extends Fragment {
    private int randNum;
    private TextView result;
    private EditText answer;
    private Button btnSubmit, btnSirit;
    private List<GuessResult> guessResults;
    private GuessResultAdapter adapter;
    private RecyclerView rcv;


    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_game, container, false);

        result = view.findViewById(R.id.tAns);
        answer = view.findViewById(R.id.etAns);
        btnSubmit= view.findViewById(R.id.btnSubmit);
        btnSirit = view.findViewById(R.id.btnSirit);

        generateRandomNumber();

        rcv = view.findViewById(R.id.guessView);
        guessResults = new ArrayList<>();
        adapter = new GuessResultAdapter(guessResults);
        rcv.setAdapter(adapter);
        rcv.setLayoutManager(new LinearLayoutManager(requireContext()));

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGuess();
            }
        });

        btnSirit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAns();
            }
        });

        return view;
    }

    private void showAns(){
        result.setText("The answer was " + randNum+"\nTry again!");
        result.setTextColor(ContextCompat.getColor(requireContext(),R.color.wrong));
        updateTextAfterDelay("", 2000);
        generateRandomNumber();
    }
    private void checkGuess() {
        String guessString = answer.getText().toString();
        String output;
        if (!guessString.isEmpty()) {
            int userGuess = Integer.parseInt(guessString);

            if (userGuess == randNum) {
                result.setText("You Got It!");
                output = "You Got It!";
                result.setTextColor(ContextCompat.getColor(requireContext(),R.color.correct));
                updateTextAfterDelay("", 2000);
                generateRandomNumber();

            } else if (userGuess < randNum) {
                result.setText("Try Higher");
                output = "Try Higher";
                result.setTextColor(ContextCompat.getColor(requireContext(),R.color.black));
                updateTextAfterDelay("", 1000);
            } else {
                result.setText("Try Lower");
                output = "Try Lower";
                result.setTextColor(ContextCompat.getColor(requireContext(),R.color.black));
                updateTextAfterDelay("", 1000);
            }

            adapter.addGuessResult(new GuessResult(userGuess, output));
            //adapter.notifyDataSetChanged();
            rcv.smoothScrollToPosition(0);

        } else {
            result.setText("Please enter an answer.");

            result.setTextColor(ContextCompat.getColor(requireContext(),R.color.black));
            updateTextAfterDelay("", 1000);;
        }

    }

    private void generateRandomNumber(){
        Random r = new Random();
        randNum = r.nextInt(100) + 1;
        Toast.makeText(requireContext(), "Generated random number", Toast.LENGTH_SHORT).show();
    }

    private void updateTextAfterDelay(final String newTxt, long delayMillis) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                result.setText(newTxt);
                answer.setText(newTxt);

            }
        }, delayMillis);
    }
}