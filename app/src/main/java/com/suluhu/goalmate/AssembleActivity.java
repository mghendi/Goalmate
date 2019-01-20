package com.suluhu.goalmate;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaofeng.flowlayoutmanager.FlowLayoutManager;

import java.util.ArrayList;
import java.util.Locale;

public class AssembleActivity extends AppCompatActivity {

    int i = 0;
    LinearLayout[] pageArray;
    TextView tvProgress;
    FloatingActionButton fab;
    String[] dataset = {"Music", "Food and drink","Active","Learn","Festival", "Party", "Arts and Entertainment" , "Business","Tech", "Health and wellness", "DIY" ," Cultural", "Tour","Religion", "Charity", "Politics", "Sports", "Family friendly","Parenting","Comedy", "Fashion", "Seasonal","Science"} ;
    ArrayList<String> mHobbies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assemble);

        LinearLayout one = findViewById(R.id.assemble_one),
                two = findViewById(R.id.assemble_two),
                three = findViewById(R.id.assemble_three),
                four = findViewById(R.id.assemble_four);
        pageArray = new LinearLayout[]{one, two, three, four};
        tvProgress = findViewById(R.id.progress);
        fab = findViewById(R.id.fab);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new FlowLayoutManager());
        recyclerView.setAdapter(new HobbyAdapter(dataset));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parsePage(true);
            }
        });
        one.setVisibility(View.VISIBLE);
        tvProgress.setText(String.format(Locale.getDefault(), "%1$d/%2$d", 1, pageArray.length));
    }

    private void parsePage(boolean progress) {
        if (progress){
            switch (i) {
                case 0:
                case 1:
                case 2:
                    i++;
                    for (LinearLayout page: pageArray) page.setVisibility(View.GONE);
                    pageArray[i].setVisibility(View.VISIBLE);
                    tvProgress.setText(String.format(Locale.getDefault(), "%1$d/%2$d", (i+1), pageArray.length));
                    fab.setImageDrawable(getResources().getDrawable(i==(pageArray.length-1)? R.drawable.ic_done_red: R.drawable.ic_next_red));
                    break;
                case 3:
                    startActivity(new Intent(AssembleActivity.this, MainActivity.class));
                    finish();
                    break;
            }
        } else {
            switch (i) {
                case 0:
                    new AlertDialog.Builder(this).setTitle("Skip Setup?")
                            .setMessage("These settings can be changed later.")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(AssembleActivity.this, MainActivity.class));
                                    finish();
                                }
                            })
                            .setNeutralButton("CANCEL", null)
                            .create().show();
                    break;
                case 1:
                case 2:
                case 3:
                    i--;
                    for (LinearLayout page: pageArray) page.setVisibility(View.GONE);
                    pageArray[i].setVisibility(View.VISIBLE);
                    tvProgress.setText(String.format(Locale.getDefault(), "%1$d/%2$d", (i+1), pageArray.length));
                    fab.setImageDrawable(getResources().getDrawable(i==(pageArray.length-1)? R.drawable.ic_done_red: R.drawable.ic_next_red));
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        parsePage(false);
    }
}
