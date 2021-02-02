package edu.ranken.jpscott.bmi02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GroupStatsActivity extends AppCompatActivity {
    TextView tvResults;
    //Button buttonReturnToHomePage;

    Integer totalUnderweight;
    Integer totalOptimalweight;
    Integer totalOverweight;
    Integer totalObese;
    String  result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_stats);
        tvResults = findViewById(R.id.tvResults);
        //buttonReturnToHomePage = findViewById(R.id.btnReturn);

        //  Get the intent in the target activity
        Intent intent = getIntent();

        //  Get the attached bundle from the intent
        Bundle extras = intent.getExtras();

        //  Extract the stored data from the bundle
        if (extras != null)
        {
            if (extras.containsKey("totalUnderweight"))
            {
                totalUnderweight = extras.getInt("totalUnderweight", 0);
            }

            if (extras.containsKey("totalOptimalweight"))
            {
                totalOptimalweight = extras.getInt("totalOptimalweight", 0);
            }

            if (extras.containsKey("totalOverweight"))
            {
                totalOverweight = extras.getInt("totalOverweight", 0);
            }

            if (extras.containsKey("totalObese"))
            {
                totalObese = extras.getInt("totalObese", 0);
            }
        }

        result = "\tTotal Underweight: "         + String.valueOf(totalUnderweight);
        result += "\n\n\tTotal Optimal Weight: "  + String.valueOf(totalOptimalweight);
        result += "\n\n\tTotal Overweight: "      + String.valueOf(totalOverweight);
        result += "\n\n\tTotal Obese: "           + String.valueOf(totalObese);

        tvResults.setText(result);

        /*buttonReturnToHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //startActivity(intent);
                finish();
            }
        });*/
    }
}
