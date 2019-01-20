package com.suluhu.goalmate;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class HobbyAdapter extends RecyclerView.Adapter<HobbyAdapter.ViewHolder> {
    private String[] mDataset;
    private AssembleActivity activity = new AssembleActivity();

    HobbyAdapter(String[] mDataset) {
        this.mDataset = mDataset;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView mCardView;
        TextView mTextView;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCardView = itemView.findViewById(R.id.cardview);
            mTextView = itemView.findViewById(R.id.textview);
            mCardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.cardview:
                    if (!activity.mHobbies.contains(mTextView.getText().toString())){
                        activity.mHobbies.add(mTextView.getText().toString());
                        mCardView.setCardBackgroundColor(v.getContext().getResources().getColor(R.color.colorPrimary));
                        mTextView.setTextColor(v.getContext().getResources().getColor(R.color.red));
                    } else {
                        activity.mHobbies.remove(mTextView.getText().toString());
                        mCardView.setCardBackgroundColor(v.getContext().getResources().getColor(R.color.red));
                        mTextView.setTextColor(v.getContext().getResources().getColor(R.color.colorPrimary));
                    }
                    break;
            }
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_hobby, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.mTextView.setText(mDataset[i]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}