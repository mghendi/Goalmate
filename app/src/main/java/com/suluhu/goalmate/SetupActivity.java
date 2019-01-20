package com.suluhu.goalmate;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class SetupActivity extends AppCompatActivity {

    boolean permissionsGranted = true;
    int i = 0, arrayLength = 5;
    private final static int PERMISSIONS_REQUEST = 100;
    LinearLayout[] pageArray = new LinearLayout[arrayLength];
    TextView tvProgress;
    CheckBox cbAgreement;
    Button btnRequest;
    FloatingActionButton fab;
    Snackbar snackbar;
    String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.INTERNET, Manifest.permission.READ_CALENDAR, Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_CALENDAR,
            Manifest.permission.WRITE_CONTACTS, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        LinearLayout one = findViewById(R.id.setup_one),
                two = findViewById(R.id.setup_two),
                three = findViewById(R.id.setup_three),
                four = findViewById(R.id.setup_four);
        pageArray = new LinearLayout[]{one, two, three, four};
        tvProgress = findViewById(R.id.progress);
        cbAgreement = findViewById(R.id.cbAgreement);
        btnRequest = findViewById(R.id.btnRequest);
        fab = findViewById(R.id.fab);

        cbAgreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAgreement.isChecked() && snackbar.isShown()) snackbar.dismiss();
            }
        });
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(SetupActivity.this, permissions, PERMISSIONS_REQUEST);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parsePage(true);
            }
        });
        snackbar = Snackbar.make(findViewById(R.id.parentView), "", Snackbar.LENGTH_INDEFINITE);

        one.setVisibility(View.VISIBLE);
        tvProgress.setText(String.format(Locale.getDefault(), "%1$d/%2$d", 1, pageArray.length));
    }

    private void parsePage(boolean progress) {
        if (progress){
            switch (i) {
                case 0:
                case 1:
                case 2:
                    // For License Agreement
                    if (i==1 && !cbAgreement.isChecked()){
                        snackbar.setText("You must accept the terms and conditions.");
                        snackbar.setAction("Accept", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                cbAgreement.toggle();
                            }
                        });
                        snackbar.show();
                        break;
                    }
                    // For Permissions
                    if(i==2) {
                        for (String permission : permissions) permissionsGranted = permissionsGranted
                                    && ContextCompat.checkSelfPermission(this, permission)
                                    == PackageManager.PERMISSION_GRANTED;
                        if(!permissionsGranted){
                            snackbar.setText("You must accept the app permissions.");
                            snackbar.setAction("Request", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ActivityCompat.requestPermissions(SetupActivity.this, permissions, PERMISSIONS_REQUEST);
                                }
                            });
                            snackbar.show();
                            break;
                        }
                    }
                    i++;
                    for (LinearLayout page: pageArray) page.setVisibility(View.GONE);
                    pageArray[i].setVisibility(View.VISIBLE);
                    tvProgress.setText(String.format(Locale.getDefault(), "%1$d/%2$d", (i+1), pageArray.length));
                    fab.setImageDrawable(getResources().getDrawable(i==(pageArray.length-1)? R.drawable.ic_done: R.drawable.ic_next));
                    break;
                case 3:
                    PreferenceManager.getDefaultSharedPreferences(this)
                            .edit().putBoolean("firstRun", false).apply();
                    startActivity(new Intent(SetupActivity.this, LoginActivity.class));
                    finish();
                    break;
            }
        } else {
            switch (i) {
                case 0:
                    new AlertDialog.Builder(this).setTitle("Cancel Setup?")
                            .setMessage("This wizard will run the next time you open the app.")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
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
                    fab.setImageDrawable(getResources().getDrawable(i==(pageArray.length-1)? R.drawable.ic_done: R.drawable.ic_next));
                    break;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSIONS_REQUEST:
                boolean granted = true;
                for (int result: grantResults) granted = granted && result == PackageManager.PERMISSION_GRANTED;
                for (String permission : permissions) permissionsGranted = permissionsGranted
                        && ContextCompat.checkSelfPermission(this, permission)
                        == PackageManager.PERMISSION_GRANTED;
                if (granted) parsePage(true);
                else snackbar.show();
        }
    }

    @Override
    public void onBackPressed() {
        parsePage(false);
    }
}
