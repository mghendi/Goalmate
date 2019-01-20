package com.suluhu.goalmate.Chat;

import android.support.design.card.MaterialCardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.suluhu.goalmate.R;

/**
 * Created by manel on 10/31/2017.
 */

public class ChatViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView mMessage;
    public LinearLayout mContainer;
    public MaterialCardView mCard;
    public ChatViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);

        mMessage = itemView.findViewById(R.id.message);
        mContainer = itemView.findViewById(R.id.container);
        mCard = itemView.findViewById(R.id.chatCard);
    }

    @Override
    public void onClick(View view) {
    }
}
