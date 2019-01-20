package com.suluhu.goalmate.Explore;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.suluhu.goalmate.R;

public class ExploreActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ExploreObject[] exploreObjects = new ExploreObject[]{
            new ExploreObject(1, "REAL TIME CASH MANAGEMENT IN ORGANIZATIONS THROUGH THE USE OF NEW AGE TOOLS.", "24-01-2019 0730", "https://www.eventbrite.com/e/real-time-cash-management-in-organizations-through-the-use-of-new-age-tools-for-easier-financial-tickets-54524412987?aff=ebdshpcalendarsection", "Business", "Nairobi"),
            new ExploreObject(2, "Gospel Karaoke with Karis", "25-01-2019 1730", "https://www.eventbrite.com/e/gospel-karaoke-with-karis-tickets-5461492069?aff=ebdssbdestsearch", "Music", "Nairobi"),
            new ExploreObject(3, "Mitsubishi Variable Frequency Drive (VFD) Workshop.", "26-01-2019 0830", "https://www.eventbrite.com/e/mitsubishi-variable-frequency-drive-vfd-workshop-tickets-54578540885?aff=ebdshpcalendarsection", "Science and Tech", "Nairobi"),
            new ExploreObject(4, "Forex traders success discussion", "26-01-2019 1400", "https://www.eventbrite.com/e/forex-traders-success-discussion-tickets-52951463252?aff=ebdshpcalendarsection", "Business", "Nairobi"),
            new ExploreObject(5, "Church in Hard Places - East Africa", "31-01-2019 1000", "https://www.eventbrite.com/e/church-in-hard-places-east-africa-tickets-53731873480?aff=ebdssbdestsearch", "Christianity", "Nairobi"),
            new ExploreObject(6, "Founders Conversation", "24-01-2019 0800", "https://www.eventbrite.com/e/founders-conversation-tickets-54077282608?aff=ebdssbdestsearch", "Networking", "Nairobi"),
            new ExploreObject(7, "Moringa School Open Day", "26-01-2019 0900", "https://www.eventbrite.com/e/moringa-school-open-day-tickets-54635160235?aff=ebdssbdestsearch", "Science and tech", "Nairobi"),
            new ExploreObject(8, "Workshop // How to align your business with the AI revolution", "29-01-2019 1800", "https://www.eventbrite.com/e/workshop-how-to-align-your-business-with-the-ai-revolution-tickets-54704023206?aff=ebdssbdestsearch", "Networking", "Nairobi"),
            new ExploreObject(9, "Digital Forensics Program: Certified Digital Forensic Examiner (CDFE)", "09-02-2019 0600", "https://www.eventbrite.com/e/digital-forensics-program-certified-digital-forensic-examiner-cdfe-tickets-52524638608?aff=ebdssbdestsearch", "Science and tech", "Nairobi"),
            new ExploreObject(10, "L. Nakuru daytrip", "01-12-2019 1200", "https://www.eventbrite.com/e/l-nakuru-daytrip-jan-26th-tickets-54338512955?aff=ebdssbdestsearch", "Travel", "Nakuru"),
            new ExploreObject(11, "Awaken in Kenya - Yoga, Ayurveda & Meditation Retreat December 2019", "09-02-2019 0600", "https://www.eventbrite.ca/e/awaken-in-kenya-yoga-ayurveda-meditation-retreat-december-2019-tickets-53156296915?aff=erelexpmlt", "Health", "Malindi"),
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        refresh();

    }

    private void refresh() {
        recyclerView = findViewById(R.id.eventrecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ExploreAdapter(exploreObjects));
    }
}
