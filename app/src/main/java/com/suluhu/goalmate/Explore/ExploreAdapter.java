package com.suluhu.goalmate.Explore;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.suluhu.goalmate.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ViewHolder> {
    ExploreObject[] mExploreObjects;

    public ExploreAdapter(ExploreObject[] mExploreObjects){
        this.mExploreObjects = mExploreObjects;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, category, datetime;
        ImageButton calendar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.eventname);
            category = itemView.findViewById(R.id.eventcategory);
            datetime = itemView.findViewById(R.id.eventtime);
            calendar = itemView.findViewById(R.id.btnCalendar);
            itemView.setOnClickListener(this);
            calendar.setOnClickListener(this);
        }

        @Override
        public void onClick(final View v) {
            switch (v.getId()){
                case R.id.btnCalendar:
                    try {
                        ExploreObject e = mExploreObjects[getAdapterPosition()];
                        Calendar calendar = Calendar.getInstance();
                        int thisMinute, thisHour, thisDay, thisMonth, thisYear;
                        Date date = new SimpleDateFormat("dd-MM-yyyy HHHH", Locale.getDefault())
                                .parse(e.getStart());
                        thisMinute = Integer.parseInt(new SimpleDateFormat("mm", Locale.getDefault()).format(date));
                        thisHour = Integer.parseInt(new SimpleDateFormat("hh", Locale.getDefault()).format(date));
                        thisDay = Integer.parseInt(new SimpleDateFormat("dd", Locale.getDefault()).format(date));
                        thisMonth = Integer.parseInt(new SimpleDateFormat("MM", Locale.getDefault()).format(date));
                        thisYear = Integer.parseInt(new SimpleDateFormat("yyyy", Locale.getDefault()).format(date));
                        calendar.set(thisYear, thisMonth, thisDay, thisHour, thisMinute);
                        Intent intent = new Intent(Intent.ACTION_INSERT)
                                .setData(CalendarContract.Events.CONTENT_URI)
                                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, calendar.getTimeInMillis())
                                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, calendar.getTimeInMillis())
                                .putExtra(CalendarContract.Events.TITLE, e.getName())
                                .putExtra(CalendarContract.Events.DESCRIPTION, e.getLink())
                                .putExtra(CalendarContract.Events.ACCESS_LEVEL, CalendarContract.Events.ACCESS_PRIVATE);
                        v.getContext().startActivity(intent);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                    break;
                default:
                    new AlertDialog.Builder(v.getContext())
                            .setTitle("Open Event Link")
                            .setMessage("Would you like to open EventBrite in your browser?")
                            .setPositiveButton("Open", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    v.getContext().startActivity(new Intent(Intent.ACTION_VIEW,
                                            Uri.parse(mExploreObjects[getAdapterPosition()].getLink())));
                                }
                            })
                            .setNeutralButton("Cancel", null)
                            .create().show();
            }
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_explore, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ExploreAdapter.ViewHolder viewHolder, int i) {
        ExploreObject e = mExploreObjects[i];
        viewHolder.name.setText(e.getName());
        viewHolder.category.setText(e.getCategory());
        viewHolder.datetime.setText(e.getStart());
    }

    @Override
    public int getItemCount() {
        return mExploreObjects.length;
    }
}
