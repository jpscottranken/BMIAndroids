package edu.ranken.jpscott.bmi02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        TextView  tvBMIStatus;
        ImageView ivBMIImage;
        Drawable drawable = null;

        String bmiStatus = "";

        //  Set references to widgets above
        tvBMIStatus = findViewById(R.id.tvBMIStatus);
        ivBMIImage  = findViewById(R.id.ivBMIImage);

        //  Get the intent in the target activity
        Intent intent = getIntent();

        //  Get the attached bundle from the intent
        Bundle extras = intent.getExtras();

        //  Extract the store data from the bundle
        if (extras != null) {
            if (extras.containsKey("bmiStatus")) {
                bmiStatus = extras.getString("bmiStatus", "");
            }
        }

        switch (bmiStatus)
        {
            case "Underweight":
                drawable  = getResources().getDrawable(R.drawable.underweight);
                break;

            case "Optimal Weight":
                drawable  = getResources().getDrawable(R.drawable.optimalweight);
                break;

            case "Overweight":
                drawable  = getResources().getDrawable(R.drawable.overweight);
                break;

            case "Obese":
                drawable  = getResources().getDrawable(R.drawable.obese);
                break;

            default:
                drawable  = getResources().getDrawable(R.drawable.underweight);
                break;
        }

        ivBMIImage.setImageDrawable(drawable);
        tvBMIStatus.setText(bmiStatus);
    }
}