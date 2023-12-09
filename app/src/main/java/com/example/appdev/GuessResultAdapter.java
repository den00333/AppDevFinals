package com.example.appdev;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GuessResultAdapter extends RecyclerView.Adapter<GuessResultAdapter.ViewHolder> {

    private List<GuessResult> guessResults;

    public GuessResultAdapter(List<GuessResult> guessResults) {
        this.guessResults = guessResults;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_guess_result, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GuessResult result = guessResults.get(position);
        holder.userGuessTextView.setText("# " + String.valueOf(result.getUserGuess()));
        holder.outputTextView.setText("output: " + result.getOutput());
    }

    public void addGuessResult(GuessResult guessResult) {
        guessResults.add(0, guessResult);
        notifyItemInserted(0);
    }

    public void clearGuessResults() {
        guessResults.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return guessResults.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView userGuessTextView;
        private TextView outputTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userGuessTextView = itemView.findViewById(R.id.userGuessTextView);
            outputTextView = itemView.findViewById(R.id.outputTextView);
        }
    }

}
