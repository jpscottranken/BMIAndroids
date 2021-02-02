package edu.ranken.jpscott.bmi02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class IndividualStatsActivity extends AppCompatActivity {
    TextView tvIndstats;
    //Button btnReturnToHomePage;

    Integer height;
    Integer weight;
    Double  bmi;
    String  bmiStr;
    String  bmiStatus;
    String  result;
    DecimalFormat bmiFormat = new DecimalFormat("##0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_stats);
        //  Set references to widgets above
        tvIndstats = findViewById(R.id.tvIndStats);
        //btnReturnToHomePage = findViewById(R.id.btnReturnToHomePage);

        //  Get the intent in the target activity
        Intent intent = getIntent();

        //  Get the attached bundle from the intent
        Bundle extras = intent.getExtras();

        //  Extract the store data from the bundle
        if (extras != null) {
            if (extras.containsKey("height")) {
                height = extras.getInt("height", 0);
            }

            if (extras.containsKey("weight")) {
                weight = extras.getInt("weight", 0);
            }

            if (extras.containsKey("bmi")) {
                bmi = extras.getDouble("bmi", 0.0);
            }

            if (extras.containsKey("bmiStatus")) {
                bmiStatus = extras.getString("bmiStatus", "");
            }
        }

        bmiStr = bmiFormat.format(bmi);
        result = "\tHeight: "    + String.valueOf(height);
        result += "\n\tWeight: " + String.valueOf(weight);
        result += "\n\tBMI: "    + bmiStr;
        result += "\n\tStatus: " + bmiStatus;

        tvIndstats.setText(result);

        /*btnReturnToHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //startActivity(intent);
                //finish();
            }
        });*/
    }
}