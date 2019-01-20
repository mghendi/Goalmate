package com.suluhu.goalmate.Chat;

import android.content.Context;
import android.graphics.Color;
import android.print.PrintAttributes;
import android.support.design.card.MaterialCardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.suluhu.goalmate.R;

import java.util.List;

/**
 * Created by manel on 10/31/2017.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatViewHolders>{
    private List<ChatObject> chatList;
    private Context context;


    public ChatAdapter(List<ChatObject> matchesList, Context context){
        this.chatList = matchesList;
        this.context = context;
    }

    @Override
    public ChatViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(lp);
        ChatViewHolders rcv = new ChatViewHolders(layoutView);

        return rcv;
    }

    @Override
    public void onBindViewHolder(ChatViewHolders holder, int position) {
        holder.mMessage.setText(chatList.get(position).getMessage());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if(chatList.get(position).getCurrentUser()){
            holder.mContainer.setGravity(Gravity.RIGHT);
            holder.mMessage.setTextColor(Color.parseColor("#404040"));
            holder.mCard.setCardBackgroundColor(Color.parseColor("#F5F5F5"));
            holder.mContainer.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }else{
            holder.mContainer.setGravity(Gravity.LEFT);
            holder.mMessage.setTextColor(Color.parseColor("#FFFFFF"));
            holder.mCard.setCardBackgroundColor(Color.parseColor("#B12222"));
            holder.mContainer.setBackgroundColor(Color.parseColor("#FFFFFF"));
            // holder.mCard.setBackgroundColor(Color.parseColor("#2DB4C8")); // Blue
        }
    }

    @Override
    public int getItemCount() {
        return this.chatList.size();
    }
}
